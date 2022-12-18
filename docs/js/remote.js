// Remote should listen to arrow keys and space key and set the hash on the
// remote page

// This should be called in response to remote hash change
const basePageName = "./";

function setPresentationSlide(slideNumber) {
    currentFrame.src = basePageName + "#" + slideNumber;
    nextFrame.src = basePageName + "#" + (slideNumber + 1);
    window.opener.location.hash=slideNumber;
}

function setPresentationSlideFromHash() {
    setPresentationSlide(slideNumberFromHash());
}

window.addEventListener('hashchange', setPresentationSlideFromHash);
window.addEventListener('load', setPresentationSlideFromHash);

document.body.onkeyup = function(e) {
    if (e.code == "Space" || e.code == "ArrowRight") {
        nextSlide();
    } else if (e.code == "ArrowLeft") {
        previousSlide();
    }
};
