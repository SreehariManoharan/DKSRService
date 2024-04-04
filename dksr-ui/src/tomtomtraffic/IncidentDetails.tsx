import React, {useState, useEffect, useRef} from "react";
import {MapContainer, Marker, Popup, TileLayer, Polyline} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import {Icon, LatLngExpression, LatLngTuple} from "leaflet";

function IncidentDetails () {

    interface Coordinate {
        doubleArrayValue: number[],
        doubleValue: number
    }

    interface Incident {
        type: string;
        properties: {
            iconCategory: number;
        };
        geometry: {
            type: string;
            coordinates: Coordinate[];
        };
    }

    interface IncidentData {
        incidents: Incident[] | undefined;
    }
    const [mapCoordinates, setMapCoordinates] = useState<LatLngExpression[][]>([]);

    const mapRef = useRef(null);
    const latitude = 52.5200066;
    const longitude = 13.404954;
    const incidentDetails: IncidentData = {
        incidents: undefined
    };
    const [incidentDetailsData, setIncidentDetailsData] = useState(incidentDetails);

    useEffect(() => {
        fetchIncidentDetails()
            .then(data => {
                setMapCoordinates(parseIncidentDataAndSetMapCoordinates(data));
                console.log("Map coordinates " + mapCoordinates);
                setIncidentDetailsData(data);
            })
            .catch(error => console.error('Error:', error));
    }, []);


    function parseIncidentDataAndSetMapCoordinates (incidentData: IncidentData): LatLngExpression[][] {
        let tempMapCoordinates: LatLngExpression[][] = [];
        if (incidentData.incidents !== undefined) {
            incidentData.incidents.forEach(incident => {
                let geometry = incident.geometry;
                if (geometry.type === 'LineString') {
                    let mapLatLngExpression: LatLngExpression[] = [];
                    geometry.coordinates.forEach(coordinate => {
                        let doubleArrayValue = coordinate.doubleArrayValue;
                        let latLongsTempArray: LatLngTuple = [doubleArrayValue.at(1)!, doubleArrayValue.at(0)!];
                        // console.log("Each: ", latLongsTempArray);
                        mapLatLngExpression.push(latLongsTempArray)
                    })
                    tempMapCoordinates.push(mapLatLngExpression);
                } else {
                    let mapLatLngExpression: LatLngExpression[] = [];// If the geometry type is a Point.
                    let pointCoordinate: LatLngTuple = [incident.geometry.coordinates[ 1 ].doubleValue, incident.geometry.coordinates[ 0 ].doubleValue];
                    mapLatLngExpression.push(pointCoordinate);
                    tempMapCoordinates.push(mapLatLngExpression);
                }
            })
            }
        return tempMapCoordinates;
    }

    async function fetchIncidentDetails () {
        const response: Response = await fetch("http://localhost:5001/api/incidence", {method: 'GET', headers: {'Content-Type': 'application/json',}});
       if (!response.ok) {
            throw new Error('Failed !!');
        }
        const incidentData: IncidentData = await response.json();
        return incidentData;
    }

    // const testCoordinate: LatLngExpression[] = [[52.4955699069, 13.3050508409], [52.4963853226, 13.305154106], [52.4965140711, 13.3051742225]]

    return (
        <>
            {
                incidentDetailsData.incidents !== undefined && (
                    <MapContainer center={[latitude, longitude]} zoom={12} ref={mapRef}
                                  style={{height: "80vh", width: "80vw"}}>
                        <TileLayer
                            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                        />
                        {
                            mapCoordinates.map((eachLineCoordinate) =>
                                (
                                    <Polyline positions={eachLineCoordinate} color="red" weight={3} opacity={0.7}></Polyline>
                                )
                            )

                        }
                    </MapContainer>
                )}

        </>
    )
}

export default IncidentDetails;