// Process queries from nearest neighbor

function swapNearestOptions () {
    const coordsVisibility = document.getElementById('nearest-coords')
    const nameVisibility = document.getElementById('nearest-name')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        coordsVisibility.style.display = "none"
        nameVisibility.style.display = "block"
    }
}

function swapRadiusOptions () {
    const coordsVisibility = document.getElementById('radius-coords')
    const nameVisibility = document.getElementById('radius-name')
    if (coordsVisibility.style.display === "none") {
        nameVisibility.style.display = "none"
        coordsVisibility.style.display = "block"
    } else {
        coordsVisibility.style.display = "none"
        nameVisibility.style.display = "block"
    }
}

document.getElementById("nearest-switch").addEventListener("change", swapNearestOptions)

document.getElementById("radius-switch").addEventListener("change", swapRadiusOptions)