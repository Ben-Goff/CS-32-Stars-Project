<#assign content>

<h1 class="centered"> STARS: Super Tangible And Real System </h1> <br>
  <label id="tab-label">First, choose your function!</label>
<label id="input-label" for="text">Then, input your data!</label>
<label id="results-label" for="text">Finally, see the results!</label>
  <br>

<div class="tab-header">
  <button id="Load Stars Tab" class="tablinks active" onclick="openTab('Load Stars')">Load Stars</button>
  <button id="Naive Nearest Neighbors Search Tab" class="tablinks" onclick="openTab('Naive Nearest Neighbors Search')">Naive Nearest Neighbors Search</button>
  <button id="Nearest Neighbors Search Tab" class="tablinks" onclick="openTab('Nearest Neighbors Search')">Nearest Neighbors Search</button>
  <button id="Naive Radius Search Tab" class="tablinks" onclick="openTab('Naive Radius Search')">Naive Radius Search</button>
  <button id="Radius Search Tab" class="tablinks" onclick="openTab('Radius Search')">Radius Search</button>
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
    <input id="naive-nearest-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="naive-nearest-coords-form" method="GET" action="/naiveneighbors" class="left">
    <label for="naive-coords-count">Count: </label><br>
    <input id="naive-coords-count" name="count"> <br>
    <label for="naive-nearest-x">X Coordinate: </label><br>
    <input id="naive-nearest-x" name="x"> <br>
    <label for="naive-nearest-y">Y Coordinate: </label><br>
    <input id="naive-nearest-y" name="y"> <br>
    <label for="naive-nearest-z">Z Coordinate: </label><br>
    <input id="naive-nearest-z" name="z"> <br> <br>
    <button id="compute-naive-nearest-coords"> Search </button>
  </form>
  <form style="display:none" id="naive-nearest-name-form" method="GET" action="/naiveneighbors" class="left">
    <label for="naive-name-count">Count: </label><br>
    <input id="naive-name-count" name="count"> <br>
    <label for="naive-nearest-name">Star Name: </label><br>
    <input id="naive-nearest-name" name="name"> <br> <br>
    <button id="naive-compute-nearest-name"> Search </button>
  </form>
</div>

<div style="display:none" id='Nearest Neighbors Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nearest-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
<form id="nearest-coords-form" method="GET" action="/neighbors" class="left">
  <label for="coords-count">Count: </label><br>
  <input id="coords-count" name="count"> <br>
  <label for="nearest-x">X Coordinate: </label><br>
  <input id="nearest-x" name="x"> <br>
  <label for="nearest-y">Y Coordinate: </label><br>
  <input id="nearest-y" name="y"> <br>
  <label for="nearest-z">Z Coordinate: </label><br>
  <input id="nearest-z" name="z"> <br> <br>
  <button id="compute-nearest-coords"> Search </button>
</form>
<form style="display:none" id="nearest-name-form" method="GET" action="/neighbors" class="left">
  <label for="name-count">Count: </label><br>
  <input id="name-count" name="count"> <br>
  <label for="nearest-name">Star Name: </label><br>
  <input id="nearest-name" name="name"> <br> <br>
  <button id="compute-nearest-name"> Search </button>
</form>
</div>

<div style="display:none" id='Naive Radius Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="naive-radius-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="naive-radius-coords-form" method="GET" action="/naiveradius" class="left">
    <label for="naive-coords-radius">Radius: </label><br>
    <input id="naive-coords-radius" name="radius"> <br>
    <label for="naive-radius-x">X Coordinate: </label><br>
    <input id="naive-radius-x" name="x"> <br>
    <label for="naive-radius-y">Y Coordinate: </label><br>
    <input id="naive-radius-y" name="y"> <br>
    <label for="naive-radius-z">Z Coordinate: </label><br>
    <input id="naive-radius-z" name="z"> <br> <br>
    <button id="naive-compute-radius-coords"> Search </button>
  </form>
  <form style="display:none" id="naive-radius-name-form" method="GET" action="/naiveradius" class="left">
    <label for="naive-name-radius">Radius: </label><br>
    <input id="naive-name-radius" name="radius"> <br>
    <label for="naive-radius-name">Star Name: </label><br>
    <input id="naive-radius-name" name="name"> <br> <br>
    <button id="naive-compute-radius-name"> Search </button>
  </form>
</div>

<div style="display:none" id='Radius Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="radius-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
  <form id="radius-coords-form" method="GET" action="/radius" class="left">
    <label for="coords-radius">Radius: </label><br>
    <input id="coords-radius" name="radius"> <br>
    <label for="radius-x">X Coordinate: </label><br>
    <input id="radius-x" name="x"> <br>
    <label for="radius-y">Y Coordinate: </label><br>
    <input id="radius-y" name="y"> <br>
    <label for="radius-z">Z Coordinate: </label><br>
    <input id="radius-z" name="z"> <br> <br>
    <button id="compute-radius-coords"> Search </button>
  </form>
  <form style="display:none" id="radius-name-form" method="GET" action="/radius" class="left">
    <label for="name-radius">Radius: </label><br>
    <input id="name-radius" name="radius"> <br>
    <label for="radius-name">Star Name: </label><br>
    <input id="radius-name" name="name"> <br> <br>
    <button id="compute-radius-name"> Search </button>
  </form>
</div>

<div id="results"> Results: <br>
  <p class="left"> ${results} </p>
</div>


</#assign>
<#include "main.ftl">