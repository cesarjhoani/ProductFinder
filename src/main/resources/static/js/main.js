//alert("ensayo")
// el atributo load espera a que el contenido de la página esté completamente cargado
window.addEventListener('load', function() {
    // Agrega la clase 'loaded' al cuerpo después de medio segundo
    setTimeout(function() {
        document.body.classList.add('loaded');
    }, 400);
});
