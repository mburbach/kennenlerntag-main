import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { App } from './App';
import './styles.css';

const container = document.getElementById('root');

if (!container) {
  throw new Error('Root-Element nicht gefunden.');
}

// Bewusst ohne StrictMode: der wuerde Effekte im Dev-Modus doppelt ausfuehren
// und damit jeden Seitenaufruf zwei Requests an /vehicles schicken.
// Fuer die Analyse der Ladezeiten soll ein Seitenaufruf genau ein Request sein.
createRoot(container).render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
);
