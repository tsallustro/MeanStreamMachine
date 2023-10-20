'use strict';

const React = require('react');
const ReactDOM = require('react-dom');


class App extends React.Component{

    render(){
    return(
        <p>This is react</p>
    )}
}


ReactDOM.render(
	<App />,
	<DetailsTable />,
)