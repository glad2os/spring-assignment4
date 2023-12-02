import App from './App.svelte'
import 'bootstrap/dist/css/bootstrap.min.css';

const app = new App({
    // @ts-ignore
    target: document.getElementById('app'),
})

export default app
