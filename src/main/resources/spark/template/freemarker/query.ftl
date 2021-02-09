<#assign content>

<h1> Stars </h1>

<div class="left"> Nearest Neighbors Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nearest-switch" type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
<form id="nearest-coords" method="GET" action="/neighbors" class="left">
  <label for="text">x </label><br>
  <input id="x" name="text" id="text"> <br>
  <label for="text">y </label><br>
  <input id="y" name="text" id="text"> <br>
  <label for="text">z </label><br>
  <input id="z" name="text" id="text"> <br>
  <button id="compute-nearest"> search </button>
</form>
<form id="nearest-name" method="GET" action="/neighbors" class="left">
  <label for="text">Star name: </label><br>
  <input id="name" name="text" id="text"> <br>
  <button id="compute-nearest"> search </button>
</form>
</div>

<div class="right"> Radius Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="radius-switch" type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
<form id="radius-coords" method="GET" action="/radius" class="left">
  <label for="text">x </label><br>
  <input id="x" name="text" id="text"> <br>
  <label for="text">y </label><br>
  <input id="y" name="text" id="text"> <br>
  <label for="text">z </label><br>
  <input id="z" name="text" id="text"> <br>
  <button id="compute-radius"> search </button>
</form>
<form id="radius-name" method="GET" action="/radius" class="right">
  <label for="text">Enter query here: </label><br>
  <input id="radius-input" name="text" id="text"> <br>
  <button id="compute-radius"> search </button>
</form>
</div>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

<h1> Search Results </h1>
  ${results}


</#assign>
<#include "main.ftl">