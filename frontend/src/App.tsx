import { NavLink, Navigate, Route, Routes } from 'react-router-dom';
import { VehicleListPage } from './pages/VehicleListPage';

export function App() {
  return (
    <div className="app">
      <nav className="app__nav">
        <span className="app__brand">Vehicle Manager</span>
        <NavLink
          to="/vehicles"
          className={({ isActive }) => (isActive ? 'app__link app__link--active' : 'app__link')}
        >
          Fahrzeuge
        </NavLink>
      </nav>

      <main className="app__main">
        <Routes>
          <Route path="/" element={<Navigate to="/vehicles" replace />} />
          <Route path="/vehicles" element={<VehicleListPage />} />
          <Route path="*" element={<p className="empty">Seite nicht gefunden.</p>} />
        </Routes>
      </main>
    </div>
  );
}
