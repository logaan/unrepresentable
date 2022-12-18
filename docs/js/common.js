function slideNumberFromHash() {
    const indexAsString = window.location.hash.substr(1);

    if(indexAsString !== "") {
        return parseInt(indexAsString);
    } else {
        return 0;
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

