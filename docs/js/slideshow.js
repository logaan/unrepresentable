function slideNumberFromHash() {
    const indexAsString = window.location.hash.substr(1);

    if(indexAsString !== "") {
        return parseInt(indexAsString);
    } else {
        return 0;
    }
}

function showSlideFromHash() {
    const slides = Array.from(document.getElementsByClassName("slide"));

    console.log(slideNumberFromHash());

    if(slideNumberFromHash() >= 0) {
        slides.forEach(function(slide) { slide.style.display = "none"; });
        slides[slideNumberFromHash()].style.display = "block";
    } else {
        console.error("Can not display negative slides.");
    }
}

function setIndexInHash(number) {
    window.location.hash = "#" + number.toString();
}

function nextSlide() {
    setIndexInHash(slideNumberFromHash() + 1);
}

function previousSlide() {
    setIndexInHash(slideNumberFromHash() - 1);
}

window.addEventListener('hashchange', showSlideFromHash);
window.addEventListener('load', showSlideFromHash);

document.body.onkeyup = function(e) {
    if (e.key == " " || e.code == "ArrowRight") {
        nextSlide();
    } else if (e.key == "ArrowLeft") {
        previousSlide();
    }
};
