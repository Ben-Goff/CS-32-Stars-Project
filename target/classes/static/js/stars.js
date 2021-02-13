// Process queries from nearest neighbor

function swapNearestOptions () {
    const coordsVisibility = document.getElementById('nearest-coords')
    const nameVisibility = document.getElementById('nearest-name')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("nearest-switch").addEventListener("change", swapNearestOptions)

function swapRadiusOptions () {
    const coordsVisibility = document.getElementById('radius-coords')
    const nameVisibility = document.getElementById('radius-name')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        nameVisibility.style.display = "block"
        coordsVisibility.style.display = "none"
    }
}

document.getElementById("radius-switch").addEventListener("change", swapRadiusOptions)

function openTab(tabName) {
    const tabs = document.getElementsByClassName("tab");
    for (let n = 0; n < tabs.length; n++) {
        tabs[n].style.display = "none"
    }
    document.getElementById("Load Stars Tab").className = "tablinks"
    document.getElementById("Nearest Neighbors Search Tab").className = "tablinks"
    document.getElementById("Radius Search Tab").className = "tablinks"
    document.getElementById(tabName).style.display = "block"
    document.getElementById(tabName + " Tab").className += " active"
}