import logo from './logo.svg';
import './App.css';
import AuthState from "./context/auth/AuthState";
import AlertState from "./context/alert/AlertState";
import {BrowserRouter} from "react-router-dom"
import Menu from './components/navigation/Menu'
import AvailableRoutes from "./components/routing/AvailableRoutes";
import EmployeeState from "./context/employee/EmployeeState";
import StockState from "./context/stock/StockState";
import CustomerState from "./context/customer/CustomerState";

function App() {
    return (
        <div className="App">
            <AuthState>
                <AlertState>
                <EmployeeState>
                    <CustomerState>
                    <StockState>
                            <BrowserRouter>
                                <Menu><AvailableRoutes></AvailableRoutes></Menu>
                            </BrowserRouter>
                    </StockState>
                    </CustomerState>
                </EmployeeState>
                </AlertState>
            </AuthState>
        </div>
    );
}

export default App;
