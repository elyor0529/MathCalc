import React from 'react';
import axios from 'axios';

import './ExpEval.css';

import d3 from "d3";

window.d3 = d3;

const functionPlot = require("function-plot");
const apiUrl = 'http://localhost:8080';
const min = -100;
const max = 100;
const formulas = [
    '1+x',
    '1-x/4*x-34',
    '4/x',
    '10-x-9',
    'x+9*x^x',
    'x/9*x^x',
    'x*23',
    '-x',
    '-9-x',
    'x/0',
    '-x/0',
    'logx(50)',
    'x*log2(x)',
    '20/x/2',
    'sinh(x)',
    '-cos(x)',
    'x*-(-cos(x))',
    'sin(x)',
    'exp(cos(x))',
    'e^x+exp(cos(x))'
];

class ExpEval extends React.Component {

    constructor(props) {

        super(props);

        this.textInput = React.createRef();

        this.state = {
            value: '1+x',
            error: '',
            pointX: 0,
            pointY: 0
        };

        setTimeout(() => {
            this.drawChart();
        });

    }

    handleSubmit = e => {

        e.preventDefault();

        this.setState({
            value: this.textInput.current.value
        });

        setTimeout(() => {
            this.drawChart();
        });

    };

    handleChange(formula) {

        this.setState({
            value: formula
        });

        this.textInput.current.value = formula;

    }

    callApi() {

        let pointX = Math.floor(Math.random() * (max - min)) + min;

        let model = {
            "formula": this.state.value.replace(/(x)/g, pointX + '')
        };

        console.log('Eval ', model);

        axios.post(apiUrl + '/math/eval', model, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(res => {

                this.setState({
                    pointX: pointX,
                    pointY: res.data
                });

            }).catch(reason => {

            this.setState({
                error: reason.toString()
            });
        });
    }

    drawChart() {

        let fns = [];

        if (this.state.value) {

            fns.push({
                fn: this.state.value,
                range: [min, max]
            });

        }

        try {

            functionPlot({
                target: '#chart1',
                data: fns,
                width: 600,
                height: 400
            });

            this.setState({
                error: ''
            });

            this.callApi();

        } catch (e) {

            this.setState({
                error: e.toString()
            })

        }

    }

    render() {

        if (this.state.error) {
            return <h2 className="text-danger">{this.state.error}</h2>;
        }

        return (
            <div>

                <div className='demo'>

                    <h4>
                        Example formulas
                    </h4>
                    <p>
                        {
                            formulas.map((t, k) =>

                                <span key={t.toString()}>
                                        <button onClick={() => this.handleChange(t)} style={{padding: 5, margin: 5}}>
                                            {t}
                                        </button>
                                </span>
                            )
                        }
                    </p>
                </div>

                <form onSubmit={this.handleSubmit}>
                    <label>
                        y= <input type="text" ref={this.textInput} defaultValue={this.state.value}/>
                    </label>
                    <input type="submit" value="Draw"/>
                </form>

                <p>
                    Random point <strong>[x={this.state.pointX};y={this.state.pointY}]</strong> to call API.
                </p>

                <div id="chart1"></div>

            </div>
        );
    }

}

export default ExpEval;