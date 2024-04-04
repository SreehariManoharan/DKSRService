import React from "react";
import "./App.css"
import {BrowserRouter} from 'react-router-dom';
import {HomePage} from "./home/HomePage";


export default function App() {
    return (
              <BrowserRouter>
                  <HomePage></HomePage>
              </BrowserRouter>
    );
}
