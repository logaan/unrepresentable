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
    if (e.code == "Space" || e.code == "ArrowRight") {
        nextSlide();
    } else if (e.code == "ArrowLeft") {
        previousSlide();
    } else if (e.code == "Digit0") {
        showSlide(0);
    } else if (e.code == "KeyR") {
        openRemote();
    }
};
