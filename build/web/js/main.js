// const value

const minFontSize = 16;
const maxFontSize = 24;
const smallDeviceBreakpoint = 768;
const largeDeviceBreakPoint = 1200;

// --------------------------------------------------

// responsive value

var prevNavLocation = window.scrollY;

// --------------------------------------------------

// const element

const html = document.getElementsByTagName("html")[0];

const header = document.getElementsByTagName("header")[0];
const nav = document.getElementsByTagName("nav")[0];
const navBar = document.getElementsByClassName("nav-bar")[0];
const navBarToggleContainer = document.getElementsByClassName("nav-bar-toggle-container")[0];

const main = document.getElementsByTagName("main")[0];

const footer = document.getElementsByTagName("footer")[0];
const socialLinkContainer = document.getElementsByClassName("social-link-container")[0];
const socialLinks = document.getElementsByClassName("social-link");
const backToTopContainer = document.getElementsByClassName("back-to-top-container")[0];
const backToTop = document.getElementsByClassName("back-to-top")[0];

// --------------------------------------------------

// add multiple event listeners to one element

function addEventListeners(element, events, func, funcArgs) {
    var functionCompiled = function () {
        func.apply(this, funcArgs);
    }

    for (var i = 0; i < events.length; i++) {
        element.addEventListener(events[i], functionCompiled);
    }
}

// --------------------------------------------------

// event listeners

window.addEventListener("resize", function () {
    squareImgWidth();
    squareImgHeight();
    responsiveNavBarMobile();
    responsiveFooter();
});

window.addEventListener("scroll", function () {
    responsiveNavBarMobile();
    responsiveNavScroll();
    responsiveBackToTop();
});

// social links
Array.from(socialLinks).forEach(function (e) {
    addEventListeners(e, ["mouseenter", "mouseleave"], imgOpacityTransition, [e]);
});

// element that has tabindex
document.onkeydown = (e) => {
    if (e.key == "Enter") {
        document.activeElement.click();
    }
};

// back to top
addEventListeners(backToTop, ["mouseenter", "mouseleave"], imgOpacityTransition, [backToTop]);

// --------------------------------------------------

// init

function init() {
    squareImgWidth();
    squareImgHeight();
    responsiveBackToTop();
    responsiveFooter();
}

// --------------------------------------------------

// funcs

function imgOpacityTransition(container) {
    const imgs = container.children;

    const firstImg = imgs[0];
    const secondImg = imgs[1];

    if (firstImg.style.opacity == 1) {
        firstImg.style.opacity = "0";
        secondImg.style.opacity = "1";
    } else {
        firstImg.style.opacity = "1";
        secondImg.style.opacity = "0";
    }
}

function responsiveNavScroll() {
    const scrollPosition = window.scrollY;

    if (scrollPosition > prevNavLocation) {
        header.style.display = "none";
    } else {
        header.style.display = "block";
    }

    prevNavLocation = scrollPosition;
}

function responsiveBackToTop() {
    const viewportHeight = window.innerHeight;
    const scrollPosition = window.scrollY;

    if (scrollPosition <= viewportHeight) {
        backToTopContainer.style.display = "none";
    } else {
        backToTopContainer.style.display = "block";
    }
}

function toggleUpdate(formID, formClass) {
    const form = document.getElementById(formID);
    const toggleStatus = form.getAttribute("data-toggle");

    switch (toggleStatus) {
        case "off":
            const allForms = document.getElementsByTagName("form");

            Array.from(allForms).forEach(function (e) {
                if (e.classList.contains(formClass)) {
                    e.style.display = "none";
                    e.setAttribute("data-toggle", "off");
                }
            });

            form.style.display = "flex";
            form.setAttribute("data-toggle", "on");
            break;

        case "on":
        default:
            form.style.display = "none";
            form.setAttribute("data-toggle", "off");
            break;
    }
}

function toggleNavBarMobile() {
    const toggleStatus = navBarToggleContainer.getAttribute("data-toggle");
    const top = navBarToggleContainer.getElementsByClassName("top")[0];
    const bottom = navBarToggleContainer.getElementsByClassName("bottom")[0];

    switch (toggleStatus) {
        case "off":
            navBar.style.display = "flex";
            top.style.opacity = "0"
            bottom.style.opacity = "1"
            navBarToggleContainer.setAttribute("data-toggle", "on");
            break;

        case "on":
            navBar.style.display = "none";
            top.style.opacity = "1"
            bottom.style.opacity = "0"
            navBarToggleContainer.setAttribute("data-toggle", "off");
            break;
    }
}

function responsiveNavBarMobile() {
    if (window.innerWidth > smallDeviceBreakpoint) {
        navBar.style.display = "flex";
    } else {
        navBar.style.display = "none";
        navBarToggleContainer.getElementsByClassName("top")[0].style.opacity = "1";
        navBarToggleContainer.getElementsByClassName("bottom")[0].style.opacity = "0";
    }
}

function responsiveFooter() {
    if (main.offsetHeight < (window.innerHeight - footer.offsetHeight)) {
        footer.style.position = "fixed";
        footer.style.bottom = "0";
    } else {
        footer.style.position = "static";
    }
}

function setQuantity(button, type) {
    const inputBox = button.parentElement.getElementsByTagName("input")[0];
    const value = inputBox.value;
    var quantity;

    if (!value) {
        quantity = 0;
    } else {
        quantity = Number.parseInt(value);
    }

    switch (type) {
        case '+':
            inputBox.value = ++quantity;
            break;

        case '-':
            if (quantity <= 1) {
                inputBox.value = "";
            } else {
                inputBox.value = --quantity;
            }

            break;
    }
}

function squareImgWidth() {
    var imgContainers = document.getElementsByClassName("square-img-width");

    Array.from(imgContainers).forEach(function (img) {
        img.style.height = img.offsetWidth + "px";
    });
}

function squareImgHeight() {
    var imgContainers = document.getElementsByClassName("square-img-height");

    Array.from(imgContainers).forEach(function (img) {
        img.style.width = img.offsetHeight + "px";
    });
}

// --------------------------------------------------
