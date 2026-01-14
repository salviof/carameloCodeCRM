/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* DOCK MENU */
function addPrevClass(e) {
    var target = e.target;
    if (target.getAttribute('src')) { // check if it is img
        var li = target.parentNode.parentNode;
        var prevLi = li.previousElementSibling;
        if (prevLi) {
            prevLi.className = 'prev';
        }

        target.addEventListener('mouseout', function () {
            prevLi.removeAttribute('class');
        }, false);
    }
}


/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNavlft() {
    document.getElementById("LeftSidenav").style.width = "250px";
    document.getElementById("lftmain").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNavlft() {
    document.getElementById("LeftSidenav").style.width = "0";
    document.getElementById("lftmain").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}

/* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
function openNavrgt() {
    document.getElementById("RightSidenav").style.width = "250px";
    document.getElementById("rgtmain").style.marginLeft = "-250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
function closeNavrgt() {
    document.getElementById("RightSidenav").style.width = "0";
    document.getElementById("rgtmain").style.marginLeft = "0";
    document.body.style.backgroundColor = "white";
}


function moverMenuDock() {
    margem = document.getElementById("dock").style.marginLeft;

    if (margem === "600px") {

        document.getElementById("dock").style.marginLeft = "0px";
    } else {

        document.getElementById("dock").style.marginLeft = "600px";
    }
}
