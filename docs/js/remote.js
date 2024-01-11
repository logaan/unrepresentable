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

document.body.onkeydown = function(e) {
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
    } else {
        console.log("e.code", e.code, e);
    }
};

function displayTime() {
    const d = new Date();
    const formattedTime = d.toLocaleTimeString('en-AU', {timeStyle: "short"});
    currentTime.innerHTML = formattedTime;
}
setInterval(displayTime, 1000);
window.addEventListener('load', displayTime);
