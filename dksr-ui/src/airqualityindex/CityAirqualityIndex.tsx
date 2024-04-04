import React, {useEffect, useState} from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import "./CityAirqualityIndex.css"
import {Icon} from "leaflet";


function CityAirqualityIndex() {
    interface Station {
        name: string;
        time: string;
    }

    interface DataItem {
        lat: number;
        lon: number;
        uid: number;
        aqi: string;
        station: Station;
    }
    interface AirQualityIndexBound {
        status: string;
        data: DataItem[];
    }

    const airQualityIndexResult: AirQualityIndexBound = {
        status: "ok",
        data: [
            {

                lat: 0,
                lon: 0,
                uid: 0,
                aqi: "",
                station: {
                    name: "",
                    time: ""
                }
            }
        ]
    }
    useEffect(() => {
        fetchAirQualityIndexData()
            .then(data => setAirQualityIndexData(data))
            .catch(error => console.error('Error', error));
    }, []);

    const [airQualityIndexData, setAirQualityIndexData] = useState(airQualityIndexResult);

    async function fetchAirQualityIndexData () {
        console.log("Inside fetchAirQualityIndexData");
        const response: Response = await fetch("http://localhost:5000/api/airqualityindex/Berlin",
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    "token": "cf9f5b21b9bcd77fbbdd6198766a7a3338a3dcf6"
                })
            })
        if(!response.ok) {
            throw new Error("Failed !!!");
        }
        const data: AirQualityIndexBound = await response.json();
        return data;
    }

    const latitude = 52.5200066;
    const longitude = 13.404954;

    const airQualityIndexIcon = new Icon ({
        iconUrl: 'https://waqi.info/mapicon/59.30.png',
        iconSize : [85,85], // size of the icon
        iconAnchor : [22,94], // point of the icon which will correspond to marker's location
        popupAnchor : [-3, -76] // point from which the popup should open relative to the iconAnchor
    })


    return (
        <>
            <MapContainer  center={[latitude, longitude]} zoom={13} style={{height: "80vh", width: "80vw"}}>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {
                    airQualityIndexData.data.map(((airQualityIndex, index) => (
                        <div key={index}>
                            <Marker position={[airQualityIndex.lat, airQualityIndex.lon]} icon= {new Icon ({
                            iconUrl: 'https://waqi.info/mapicon/'+airQualityIndex.aqi+'.30.png',
                            iconSize : [85,85], // size of the icon
                            iconAnchor : [22,94], // point of the icon which will correspond to marker's location
                            popupAnchor : [-3, -76] // point from which the popup should open relative to the iconAnchor
                        })}>
                                <Popup>
                                    <b>{airQualityIndex.aqi}</b>
                                    <em>{airQualityIndex.station.name}</em>
                                </Popup>
                            </Marker>
                        </div>
                    )))
                }
            </MapContainer>
        </>

    );
    }


export default CityAirqualityIndex;