/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function abrirURLComLoad(idComponente, url) {
    bloquearArea(idComponente);
    location.replace(url);
}