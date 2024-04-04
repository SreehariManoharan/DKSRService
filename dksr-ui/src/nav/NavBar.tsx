import { Link } from 'react-router-dom';

const NavBar = () => {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/airqualityindex">Air Quality Index</Link>
                </li>
                <li>
                    <Link to="/incidence">Incidents</Link>
                </li>
            </ul>
        </nav>
    );
};

export default NavBar;