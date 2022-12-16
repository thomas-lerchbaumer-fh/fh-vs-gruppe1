import logo from './logo.svg';
import './App.css';
import AuthState from "./context/auth/AuthState";
import AlertState from "./context/alert/AlertState";
import {BrowserRouter} from "react-router-dom"
import Menu from './components/navigation/Menu'
import AvailableRoutes from "./components/routing/AvailableRoutes";
import EmployeeState from "./context/employee/EmployeeState";
import StockState from "./context/stock/StockState";

function App() {
    return (
        <div className="App">
            <AuthState>
                <EmployeeState>
                    <StockState>
                        <AlertState>
                            <BrowserRouter>
                                <Menu><AvailableRoutes></AvailableRoutes></Menu>
                            </BrowserRouter>
                        </AlertState>
                    </StockState>
                </EmployeeState>
            </AuthState>
        </div>
    );
}

export default App;
