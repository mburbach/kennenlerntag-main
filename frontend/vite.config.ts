import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// Der Vite-Dev-Server liefert das Frontend auf Port 5173 aus und leitet alle
// Requests unter /api an den Spring-Boot-Service auf Port 8080 weiter.
// Dadurch bleibt das Backend unveraendert (kein CORS-Setup noetig).
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
});
