import process from 'node:process';
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// Der Vite-Dev-Server liefert das Frontend auf Port 5173 aus und leitet alle
// Requests unter /api an den Spring-Boot-Service weiter. Dadurch bleibt das
// Backend unveraendert (kein CORS-Setup noetig).
//
// Lokal zeigt der Proxy auf http://localhost:8080. Laeuft das Frontend im
// Container (docker-compose.yml), setzt der Container API_PROXY_TARGET auf
// den Host, weil das Backend dort ausserhalb von Compose gestartet wird.
const apiProxyTarget = process.env.API_PROXY_TARGET ?? 'http://localhost:8080';

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: apiProxyTarget,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
});
