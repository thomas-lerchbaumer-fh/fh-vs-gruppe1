import logo from './logo.svg';
import './App.css';
import AuthState from "./context/auth/AuthState";
import AlertState from "./context/alert/AlertState";
import {BrowserRouter} from "react-router-dom"
import Menu from './components/navigation/Menu'
import AvailableRoutes from "./components/routing/AvailableRoutes";

function App() {
  return (
      <div className="App">
        <AuthState>

              <AlertState>
                <BrowserRouter>
                  <Menu><AvailableRoutes></AvailableRoutes></Menu>
                </BrowserRouter>
              </AlertState>

        </AuthState>
      </div>
  );
}

export default App;
