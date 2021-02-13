<#assign content>

<h1 class="centered"> STARS: Super Tangible And Real System </h1> <br>
  <label id="tab-label">First, choose your function!</label>
<label id="input-label" for="text">Then, input your data!</label>
<label id="results-label" for="text">Finally, see the results!</label>
  <br>

<div class="tab-header">
  <button id="Load Stars Tab" class="tablinks active" onclick="openTab('Load Stars')">Load Stars</button>
  <button id="Naive Nearest Neighbors Search Tab" class="tablinks" onclick="openTab('Naive Nearest Neighbors Search')">Naive Nearest Neighbors Search</button>
  <button id="Nearest Neighbors Search Tab" class="tablinks" onclick="openTab('Nearest Neighbors Search')">KDTree Nearest Neighbors Search</button>
  <button id="Naive Radius Search Tab" class="tablinks" onclick="openTab('Naive Radius Search')">Naive Radius Search</button>
  <button id="Radius Search Tab" class="tablinks" onclick="openTab('Radius Search')">KDTree Radius Search</button>
</div>

  <div id='Load Stars' class="tab">
    <form id="load" method="GET" action="/load" class="left">
      <label for="text">1: Enter the Path to the .csv File Containing Star Data</label><br>
      <label for="path">Path: </label><br>
      <input id="path" name="path"> <br> <br>
    <button id="load-stars"> Load </button>
  </form>
</div>

<div style="display:none" id='Naive Nearest Neighbors Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nn-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="nn-coords-form" method="GET" action="/naiveneighbors" class="left">
    <label for="nn-coords">Count: </label><br>
    <input id="nn-coords" name="nn-coords"> <br>
    <label for="nn-x">X Coordinate: </label><br>
    <input id="nn-x" name="nn-x"> <br>
    <label for="nn-y">Y Coordinate: </label><br>
    <input id="nn-y" name="nn-y"> <br>
    <label for="nn-z">Z Coordinate: </label><br>
    <input id="nn-z" name="nn-z"> <br> <br>
    <button id="nn-compute-coords"> Search </button>
  </form>
  <form style="display:none" id="nn-name-form" method="GET" action="/naiveneighbors" class="left">
    <label for="nn-count">Count: </label><br>
    <input id="nn-count" name="nn-count"> <br>
    <label for="nn-name">Star Name: </label><br>
    <input id="nn-name" name="nn-name"> <br> <br>
    <button id="nn-compute-name"> Search </button>
  </form>
</div>

<div style="display:none" id='Nearest Neighbors Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="n-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
<form id="n-coords-form" method="GET" action="/neighbors" class="left">
  <label for="n-coords">Count: </label><br>
  <input id="n-coords" name="n-coords"> <br>
  <label for="n-x">X Coordinate: </label><br>
  <input id="n-x" name="n-x"> <br>
  <label for="n-y">Y Coordinate: </label><br>
  <input id="n-y" name="n-y"> <br>
  <label for="n-z">Z Coordinate: </label><br>
  <input id="n-z" name="n-z"> <br> <br>
  <button id="n-compute-coords"> Search </button>
</form>
<form style="display:none" id="n-name-form" method="GET" action="/neighbors" class="left">
  <label for="n-count">Count: </label><br>
  <input id="n-count" name="n-count"> <br>
  <label for="n-name">Star Name: </label><br>
  <input id="n-name" name="n-name"> <br> <br>
  <button id="n-compute-name"> Search </button>
</form>
</div>

<div style="display:none" id='Naive Radius Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nr-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="nr-coords-form" method="GET" action="/naiveradius" class="left">
    <label for="nr-coords">Radius: </label><br>
    <input id="nr-coords" name="nr-coords"> <br>
    <label for="nr-x">X Coordinate: </label><br>
    <input id="nr-x" name="nr-x"> <br>
    <label for="nr-y">Y Coordinate: </label><br>
    <input id="nr-y" name="nr-y"> <br>
    <label for="nr-z">Z Coordinate: </label><br>
    <input id="nr-z" name="nr-z"> <br> <br>
    <button id="nr-compute-coords"> Search </button>
  </form>
  <form style="display:none" id="nr-name-form" method="GET" action="/naiveradius" class="left">
    <label for="nr-radius">Radius: </label><br>
    <input id="nr-radius" name="nr-radius"> <br>
    <label for="nr-name">Star Name: </label><br>
    <input id="nr-name" name="nr-name"> <br> <br>
    <button id="nr-compute-name"> Search </button>
  </form>
</div>

<div style="display:none" id='Radius Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="r-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="r-coords-form" method="GET" action="/radius" class="left">
    <label for="r-coords">Radius: </label><br>
    <input id="r-coords" name="r-coords"> <br>
    <label for="r-x">X Coordinate: </label><br>
    <input id="r-x" name="r-x"> <br>
    <label for="r-y">Y Coordinate: </label><br>
    <input id="r-y" name="r-y"> <br>
    <label for="r-z">Z Coordinate: </label><br>
    <input id="r-z" name="r-z"> <br> <br>
    <button id="r-compute-coords"> Search </button>
  </form>
  <form style="display:none" id="r-name-form" method="GET" action="/radius" class="left">
    <label for="r-radius">Radius: </label><br>
    <input id="r-radius" name="r-radius"> <br>
    <label for="r-name">Star Name: </label><br>
    <input id="r-name" name="r-name"> <br> <br>
    <button id="r-compute-name"> Search </button>
  </form>
</div>

<div id="results"> Results: <br>
  <p class="left"> ${results} </p>
</div>


</#assign>
<#include "main.ftl">