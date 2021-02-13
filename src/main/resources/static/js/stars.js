// Process queries from nearest neighbor

function swapNearestOptions () {
    const coordsVisibility = document.getElementById('nearest-coords-form')
    const nameVisibility = document.getElementById('nearest-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("nearest-switch").addEventListener("change", swapNearestOptions)

function swapNaiveNearestOptions () {
    const coordsVisibility = document.getElementById('naive-nearest-coords-form')
    const nameVisibility = document.getElementById('naive-nearest-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("naive-nearest-switch").addEventListener("change", swapNaiveNearestOptions)

function swapRadiusOptions () {
    const coordsVisibility = document.getElementById('radius-coords-form')
    const nameVisibility = document.getElementById('radius-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("radius-switch").addEventListener("change", swapRadiusOptions)

function swapNaiveRadiusOptions () {
    const coordsVisibility = document.getElementById('naive-radius-coords-form')
    const nameVisibility = document.getElementById('naive-radius-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("naive-radius-switch").addEventListener("change", swapNaiveRadiusOptions)

function openTab(tabName) {
    const tabs = document.getElementsByClassName("tab");
    for (let n = 0; n < tabs.length; n++) {
        tabs[n].style.display = "none"
    }
    document.getElementById("Load Stars Tab").className = "tablinks"
    document.getElementById("Naive Nearest Neighbors Search Tab").className = "tablinks"
    document.getElementById("Nearest Neighbors Search Tab").className = "tablinks"
    document.getElementById("Naive Radius Search Tab").className = "tablinks"
    document.getElementById("Radius Search Tab").className = "tablinks"
    document.getElementById(tabName).style.display = "block"
    document.getElementById(tabName + " Tab").className += " active"
}