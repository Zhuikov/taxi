import React from 'react';
import ReactDOM from 'react-dom';

import { Route, Switch, Redirect, BrowserRouter } from 'react-router-dom';

import ClientApp from "./ClientApp";
import Login from "./Login";
// import DriverApp from "./DriverApp";
// import ManagerApp from "./ManagerApp";

import axios from "axios";

class App extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            user: {
                name: '',
                surname: '',
                role: ''
            }
        }
    }

    // componentDidMount() {
    //     axios.get('/user')
    //         .then((response) => {
    //             console.log(response)
    //             this.setState({ user: {
    //                     name: response.data.name,
    //                     surname: response.data.surname,
    //                     role: response.data.role
    //                 }})
    //         })
    //         .catch((error) => {
    //             console.log(error);
    //         })
    // }

    render() {

        // let path = null;
        // if (this.state.user.role === 'CLIENT') {
        //     path = '/client'
        // } else if (this.state.user.role === 'DRIVER') {
        //     path = '/driver'
        // } else if (this.state.user.role === 'MANAGER') {
        //     path = '/manager'
        // } else if (this.state.user.role === 'OWNER') {
        //     path = '/owner'
        // }
        //
        // console.log(this.state)
        // console.log(path)

        return (
            <div className="app">
                <Login />
            </div>
        )
        // return path && (
        //     <BrowserRouter>
        //         <Switch>
        //             <Route exact path="/">
        //                 <Redirect to={path} />
        //             </Route>
        //             <Route path="/client">
        //                 <ClientApp />
        //             </Route>
        //             <Route path="/manager">
        //                 <ManagerApp />
        //             </Route>
        //             <Route path="/driver">
        //                 <DriverApp />
        //             </Route>
        //             <Route path="/owner">
        //                 <OwnerApp />
        //             </Route>
        //         </Switch>
        //     </BrowserRouter>
        // )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)
