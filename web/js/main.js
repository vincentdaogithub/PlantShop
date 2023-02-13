// --------------------------------------------------

// const value

const minFontSize = 16;
const maxFontSize = 24;
const smallDeviceBreakpoint = 768;
const largeDeviceBreakPoint = 1200;


// --------------------------------------------------

// responsive value

var prevNavLocation = window.scrollY;
var navIsHidden = false;


// --------------------------------------------------

// const element

const html = document.getElementsByTagName("html")[0];
const nav = document.getElementsByTagName("nav")[0];
const navBar = document.getElementsByClassName("nav-bar")[0];
const header = document.getElementsByTagName("header")[0];  // represents the nav
const main = document.getElementsByTagName("main")[0];
const socialLinkContainer = document.getElementsByClassName("social-link-container")[0];
const socialLinks = document.getElementsByClassName("social-link");
const backToTop = document.getElementsByClassName("back-to-top")[0];
const backToTopContainer = document.getElementsByClassName("back-to-top-container")[0];
const footer = document.getElementsByTagName("footer")[0];

// --------------------------------------------------

// const class names

const content = "content";
const contentText = "content-text";
const contentImg = "content-img";
const section = "section";


// --------------------------------------------------

// event listeners

// special func to set up multiple event listeners to one element
function addEventListeners(element, events, func, funcArgs) {
    var funcCompiled = function () {
        func.apply(this, funcArgs);
    }

    for (var i = 0; i < events.length; i++) {
        element.addEventListener(events[i], funcCompiled);
    }
}

window.addEventListener("resize", function () {
    responsiveFontSize();
    setNavBar();
    setHorizontalLayout();
});

window.addEventListener("scroll", function () {
    responsiveNavScroll();
    responsiveBackToTop();
})

for (var i = 0; i < socialLinks.length; i++) {
    addEventListeners(socialLinks[i], ["mouseenter", "mouseleave", "click"], imgsOpacityTransition, [socialLinks[i]]);
}

addEventListeners(backToTop, ["mouseenter", "mouseleave", "click"], imgsOpacityTransition, [backToTop]);


// --------------------------------------------------

// funcs for page layouts

function init() {
    responsiveFontSize();
    setNavBar();
    setHorizontalLayout();
    setSocialLinks();
    setBackToTop();
    responsiveBackToTop();
    setFooter();
}

function setSocialLinks() {
    setSocialLinksOpacity();
    setSocialLinksEqualWidth();
}

function setNavBar() {
    var childrenCount = navBar.childElementCount;
    var childWidthEqual = 100 / childrenCount;
    
    for (var i = 0; i < childrenCount; i++) {
        var child = navBar.children[i];
        child.style.width = childWidthEqual + "%";
        child.style.lineHeight = navBar.offsetHeight + "px";
    }
}

function responsiveFontSize() {
    var fontSizeRange = maxFontSize - minFontSize;
    var screenWidth = html.offsetWidth;

    if (screenWidth <= smallDeviceBreakpoint) {
        html.style.fontSize = minFontSize + "px";
    } else if (smallDeviceBreakpoint < screenWidth && screenWidth <= largeDeviceBreakPoint) {
        html.style.fontSize = minFontSize + fontSizeRange
                * ((screenWidth - smallDeviceBreakpoint) / (largeDeviceBreakPoint - smallDeviceBreakpoint))
                + "px";
    } else {
        html.style.fontSize = maxFontSize + "px";
    }
}

function swapGifs(element, url1, url2) {
    if (element.getAttribute("src") == url1) {
        element.src = url2;
    } else {
        element.src = url1;
    }
}

// --------------------------------------------------

// set up the horizontal layout

function setHorizontalLayout() {
    const horizontalLayouts = document.getElementsByClassName("horizontal-layout");

    for (var i = 0; i < horizontalLayouts.length; i++) {
        accessContentContainer(horizontalLayouts[i].children);
    }
}

// sub-func for setHorizontalLayout()
function accessContentContainer(contentContainers) {
    for (var i = 0; i < contentContainers.length; i++) {
        const classes = contentContainers[i].className;

        if (classes.length > 0 && classes.includes(content)) {
            accessContents(contentContainers[i].children);
        }
    }
}

// sub-func for accessContentContainer()
function accessContents(contents) {
    for (var i = 0; i < contents.length; i++) {
        const element = contents[i];
        const classes = element.className;

        if (classes.includes(contentText) || classes.includes(contentImg)) {
            setContent(element);
        }
    }
}

// sub-func for setContent()
function setContent(content) {
    if (window.innerWidth >= largeDeviceBreakPoint) {
        content.style.cssFloat = "left";
        content.style.width = "50%";
        content.style.height = "100%";
    } else {
        content.style.cssFloat = "none";
        content.style.width = "100%";
        content.style.height = "50%";
    }
}


// --------------------------------------------------

function imgsOpacityTransition(container) {
    const imgs = container.children;

    if (imgs.length != 2) {
        return;
    }

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

function setSocialLinksEqualWidth() {
    const children = socialLinkContainer.children;
    const widthEqual = 100 / (children.length != 0 ? children.length : 1);

    for (var i = 0; i < children.length; i++) {
        children[i].style.width = widthEqual + "%";
    }
}

function setSocialLinksOpacity() {
    for (var i = 0; i < socialLinks.length; i++) {
        const imgs = socialLinks[i].children;

        for (var j = 0; j < imgs.length; j++) {
            setImgOpacity(imgs[j]);
        }
    }
}

// sub-func of setSocialLinksOpacity()
function setImgOpacity(img) {
    switch (img.className) {
        case "top":
            img.style.opacity = "1";
            break;

        case "bottom":
            img.style.opacity = "0";
            break;
    }
}

function responsiveNavScroll() {
    const scrollPosition = window.scrollY;

    if (scrollPosition > prevNavLocation) {
        header.style.display = "none";
    } else {
        header.style.display = "block";
        setNavBar();
    }

    prevNavLocation = scrollPosition;
}

function setBackToTop() {
    const imgs = backToTop.children;

    for (var j = 0; j < imgs.length; j++) {
        setImgOpacity(imgs[j]);
    }
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

function setFooter() {
    const viewportHeight = window.innerHeight;
    const footerLocation = footer.getBoundingClientRect().top;

    if (footerLocation < viewportHeight) {
        footer.style.position = "fixed";
        footer.style.bottom = "0";
    }
}