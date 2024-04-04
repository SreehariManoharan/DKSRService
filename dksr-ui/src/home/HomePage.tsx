import React from "react";
import {Routes, Route} from 'react-router-dom';
import "./homepage.css"
import CityAirqualityIndex from "../airqualityindex/CityAirqualityIndex";
import IncidentDetails from "../tomtomtraffic/IncidentDetails";
import NavBar from "../nav/NavBar";

export function HomePage () {
    return (
        <>
            <div className="container">
                <div className="links">
                    <NavBar/>
                </div>
                <div className="maps">
                    <Routes>
                        <Route path="/" Component={CityAirqualityIndex}/>
                        <Route path="/airqualityindex" Component={CityAirqualityIndex}/>
                        <Route path="/incidence" Component={IncidentDetails}/>
                    </Routes>
                </div>
            </div>
        </>
    )
}