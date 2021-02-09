// Process queries from nearest neighbor

function swapNearestOptions () {
    const visibility = document.getElementById('nearest')
        visibility.style.visibility = "hidden"
}

document.getElementById("nearest-switch").addEventListener("click", swapNearestOptions)