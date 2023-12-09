import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  alias: {
    // ... any other aliases
    'aws-sdk': 'aws-sdk/dist/aws-sdk',
  },
})
