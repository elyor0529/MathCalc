import React from 'react';
import logo from './logo.svg';
import './App.css';
import ExpEval from "./ExpEval";

function App() {

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Expression evaluator
                </a>
            </header>
            <ExpEval/>
        </div>
    );
}

export default App;
