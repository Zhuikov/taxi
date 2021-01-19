import React from 'react';
import ReactDOM from 'react-dom';

import { Route, Switch, Redirect, BrowserRouter } from 'react-router-dom';
import {InputForm, ItemTable, AcceptDecline} from './common/Compenents';

import ClientApp from "./ClientApp";
import OwnerApp from "./OwnerApp";
import DriverApp from "./DriverApp";
import ManagerApp from "./ManagerApp";

import axios from "axios";

class Login extends React.Component {

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

    loginRequest(o) {
        console.log("In loginRequest")
        axios.post("/login", o)
            .then((response) => {
                const user = response.data;
                console.log(user);
                this.setState({ user: user })
            })
            .catch((error) => {
                const message = error.response.data
                alert(`Error: ${message}`);
            })
    }

    makeLoginForm() {
        return (
            <div className="loginForm">
                <h2>
                    Login
                </h2>
                <InputForm rows={[
                    {rowName: "login", rowPlaceholder: "login"},
                ]}
                initState={{login: ""}}
                onSubmit={(o) => this.loginRequest(o)}/>
            </div>
        )
    }

    handleLogout() {
        axios.get("/logout")
            .then((response) => {
                console.log("Logout successful");
                this.setState({ user: {name: "", surname: "", role: ""} })
            })
            .catch((error) => {
                console.log(error)
            })
    }

    makeLogoutButton() {
        return (
            <div>
                <button
                    type="submit"
                    name="logout"
                    onClick={(e) => this.handleLogout()}>Logout</button>
            </div>
        )
    }

    renderApp() {
        if (this.state.user.role === 'CLIENT') {
            console.log(this.state)
            return (<ClientApp name={this.state.user.name} surname={this.state.user.surname}/>)
        }
        if (this.state.user.role === "OWNER") {
            return (<OwnerApp name={this.state.user.name} surname={this.state.user.surname}/>)
        }
        if (this.state.user.role === "MANAGER") {
            return (<ManagerApp name={this.state.user.name} surname={this.state.user.surname}/>)
        }
        if (this.state.user.role === "DRIVER") {
            return (<DriverApp name={this.state.user.name} surname={this.state.user.surname}/>)
        }
        if (this.state.user.role === "NOT_ACTIVE") {
            return (<h2>Your driver account is not activated!</h2>)
        }
        if (this.state.user.role === '') {
            return (<h2>Not logged in</h2>)
        }

    }

    render() {

        return (
            <div>
                {this.makeLoginForm()}
                {this.makeLogoutButton()}
                {this.renderApp()}
            </div>
        )

        let path = null;
        if (this.state.user.role === 'CLIENT') {
            path = '/client'
        } else if (this.state.user.role === 'DRIVER') {
            path = '/driver'
        } else if (this.state.user.role === 'MANAGER') {
            path = '/manager'
        } else if (this.state.user.role === 'OWNER') {
            path = '/owner'
        }

        console.log(this.state)
        console.log(path)

        return path && (
            <BrowserRouter>
                <Switch>
                    <Route exact path="/">
                        <Redirect to={path} />
                    </Route>
                    <Route path="/client">
                        <ClientApp />
                    </Route>
                    <Route path="/manager">
                        <ManagerApp />
                    </Route>
                    <Route path="/driver">
                        <DriverApp />
                    </Route>
                    <Route path="/owner">
                        <OwnerApp />
                    </Route>
                </Switch>
            </BrowserRouter>
        )
    }
}

export default Login