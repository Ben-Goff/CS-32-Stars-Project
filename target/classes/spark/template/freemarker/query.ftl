<#assign content>

<h1 class="centered"> STARS: Super Tangible And Real System </h1> <br>
  <label id="tab-label">First, choose your function!</label>
<label id="input-label" for="text">Then, input your data!</label>
<label id="results-label" for="text">Finally, see the results!</label>
  <br>

<div class="tab-header">
  <button id="Load Stars Tab" class="tablinks active" onclick="openTab('Load Stars')">Load Stars</button>
  <button id="Nearest Neighbors Search Tab" class="tablinks" onclick="openTab('Nearest Neighbors Search')">Nearest Neighbors Search</button>
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

<div style="display:none" id='Nearest Neighbors Search' class="tab">
  <label for="text">1: Select Input Method</label><br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nearest-switch" type="checkbox">
    <span class="slider"></span>
  </label>
  <label for="text">Star Name</label> <br><br>
  <label for="text">2: Enter Parameters</label><br>
<form id="nearest-coords" method="GET" action="/neighbors" class="left">
  <label for="count">Count: </label><br>
  <input id="count" name="count"> <br>
  <label for="x">X Coordinate: </label><br>
  <input id="x" name="x"> <br>
  <label for="y">Y Coordinate: </label><br>
  <input id="y" name="y"> <br>
  <label for="z">Z Coordinate: </label><br>
  <input id="z" name="z"> <br> <br>
  <button id="compute-nearest-coords"> Search </button>
</form>
<form style="display:none" id="nearest-name" method="GET" action="/neighbors" class="left">
  <label for="count">Count: </label><br>
  <input id="count" name="count"> <br>
  <label for="name">Star Name: </label><br>
  <input id="name" name="name" id="name"> <br> <br>
  <button id="compute-nearest-name"> Search </button>
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
  <form id="radius-coords" method="GET" action="/radius" class="left">
    <label for="radius">Radius: </label><br>
    <input id="radius" name="radius"> <br>
    <label for="x">X Coordinate: </label><br>
    <input id="x" name="x"> <br>
    <label for="y">Y Coordinate: </label><br>
    <input id="y" name="y"> <br>
    <label for="z">Z Coordinate: </label><br>
    <input id="z" name="z"> <br> <br>
    <button id="compute-radius-coords"> Search </button>
  </form>
  <form style="display:none" id="radius-name" method="GET" action="/radius" class="left">
    <label for="radius">Radius: </label><br>
    <input id="radius" name="radius"> <br>
    <label for="name">Star Name: </label><br>
    <input id="name" name="name" id="name"> <br> <br>
    <button id="compute-radius-name"> Search </button>
  </form>
</div>

<div id="results"> Results: <br>
  <p class="left"> ${results} </p>
</div>


</#assign>
<#include "main.ftl">