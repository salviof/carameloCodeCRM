const CACHE = 'estaticos-v1';

self.addEventListener('install', () => self.skipWaiting());

self.addEventListener('activate', (e) => {
    e.waitUntil(
            caches.keys()
            .then((chaves) => Promise.all(
                        chaves.filter((k) => k !== CACHE).map((k) => caches.delete(k))
                        ))
            .then(() => self.clients.claim())
            );
});

function ehEstatico(url) {
    return url.pathname.startsWith('/resources/')
            || url.pathname.startsWith('/javax.faces.resource/');
}

self.addEventListener('fetch', (e) => {
    const req = e.request;
    if (req.method !== 'GET')
        return;                 // postbacks JSF passam direto pela rede
    const url = new URL(req.url);
    if (url.origin !== location.origin)
        return;       // terceiros: rede
    if (!ehEstatico(url))
        return;                     // HTML/ajax/navegação: rede (protege o ViewState)

    // Estáticos: cache-first com preenchimento preguiçoso.
    e.respondWith(
            caches.match(req).then((hit) =>
        hit || fetch(req).then((resp) => {
            if (resp.ok) {
                const copia = resp.clone();
                caches.open(CACHE).then((c) => c.put(req, copia));
            }
            return resp;
        })
    )
            );
});