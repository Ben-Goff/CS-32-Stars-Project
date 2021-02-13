// Process queries from nearest neighbor

function swapNearestOptions () {
    const coordsVisibility = document.getElementById('n-coords-form')
    const nameVisibility = document.getElementById('n-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("n-switch").addEventListener("change", swapNearestOptions)

function swapNaiveNearestOptions () {
    const coordsVisibility = document.getElementById('nn-coords-form')
    const nameVisibility = document.getElementById('nn-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("nn-switch").addEventListener("change", swapNaiveNearestOptions)

function swapRadiusOptions () {
    const coordsVisibility = document.getElementById('r-coords-form')
    const nameVisibility = document.getElementById('r-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("r-switch").addEventListener("change", swapRadiusOptions)

function swapNaiveRadiusOptions () {
    const coordsVisibility = document.getElementById('nr-coords-form')
    const nameVisibility = document.getElementById('nr-name-form')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("nr-switch").addEventListener("change", swapNaiveRadiusOptions)

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