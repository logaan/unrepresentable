function showSlideFromHash() {
    const slides = Array.from(document.getElementsByClassName("slide"));

    if(slideNumberFromHash() >= 0) {
        slides.forEach(function(slide) { slide.style.display = "none"; });
        slides[slideNumberFromHash()].style.display = "block";
    } else {
        console.error("Can not display negative slides.");
    }
}

function openRemote() {
    window.open("remote.html", "remote");
}

window.addEventListener('hashchange', showSlideFromHash);
window.addEventListener('load', showSlideFromHash);

document.body.onkeyup = function(e) {
    if (e.code == "Space" || e.code == "ArrowRight") {
        nextSlide();
    } else if (e.code == "ArrowLeft") {
        previousSlide();
    } else if (e.code == "KeyR" && e.shiftKey) {
        openRemote();
    }
};
