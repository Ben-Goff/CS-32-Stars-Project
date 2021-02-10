// Process queries from nearest neighbor

function swapNearestOptions () {
    const coordsVisibility = document.getElementById('nearest-coords')
    const nameVisibility = document.getElementById('nearest-name')
    if (coordsVisibility.className === "hidden") {
        nameVisibility.setAttribute("class", "hidden")
        coordsVisibility.setAttribute("class", "shown")
    } else {
        nameVisibility.setAttribute("class", "shown")
        coordsVisibility.setAttribute("class", "hidden")
    }
}

function swapRadiusOptions () {
    const coordsVisibility = document.getElementById('radius-coords')
    const nameVisibility = document.getElementById('radius-name')
    if (coordsVisibility.className === "hidden") {
        nameVisibility.setAttribute("class", "hidden")
        coordsVisibility.setAttribute("class", "shown")
    } else {
        nameVisibility.setAttribute("class", "shown")
        coordsVisibility.setAttribute("class", "hidden")
    }
}

document.getElementById("nearest-switch").addEventListener("change", swapNearestOptions)

document.getElementById("radius-switch").addEventListener("change", swapRadiusOptions)