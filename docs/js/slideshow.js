function showSlide(number) {
    if(number >= 0) {
        const slides = Array.from(document.getElementsByClassName("slide"));
        slides.forEach(function(slide) { slide.style.display = "none"; });
        slides[slideNumberFromHash()].style.display = "block";
    } else {
        console.error("Can not display negative slides.");
    }
}

function showSlideFromHash() {
    showSlide(slideNumberFromHash());
}

function openRemote() {
    window.open("remote.html", "remote");
}

window.addEventListener('hashchange', showSlideFromHash);
window.addEventListener('load', showSlideFromHash);

document.body.onkeydown = function(e) {
    console.log(e.code);

    if (e.code == "Space" || e.code == "ArrowRight") {
        nextSlide();
    } else if (e.code == "ArrowLeft") {
        previousSlide();
    } else if (e.code == "Digit0") {
        window.location.hash = "0";
    } else if (e.code == "Slash") {
        alert("Space, Arrow Right: Next slide\n" +
              "Arrow Left: Previous slide\n" +
              "0: First slide\n" +
              "r: Open remote");
    } else if (e.code == "KeyR") {
        openRemote();
    }
};
