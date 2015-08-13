/**
 * Created by vlad on 22.07.15.
 */

function changeForm(form){
    document.getElementById("addForm").style.display = "none";
    document.getElementById("findForm").style.display = "none";
    document.getElementById("updateForm").style.display = "none";
    document.getElementById("deleteForm").style.display = "none";
    document.getElementById("add").className -= "activeButton";
    document.getElementById("find").className -= "activeButton";
    document.getElementById("upd").className -= "activeButton";
    document.getElementById("dlt").className -= "activeButton";


    switch (form){
        case "add" : addForm(); break;
        case "find" : findForm(); break;
        case "update" : updateForm(); break;
        case "delete" : deleteForm(); break;
    }

}



function addForm(){
    document.getElementById("addForm").style.display = "block";
    document.getElementById("add").className = "activeButton";
}

function findForm() {
    document.getElementById("findForm").style.display = "block";
    document.getElementById("find").className = "activeButton";
}

function updateForm(){
    document.getElementById("updateForm").style.display = "block";
    document.getElementById("upd").className = "activeButton";
}

function deleteForm() {
    document.getElementById("deleteForm").style.display = "block";
    document.getElementById("dlt").className = "activeButton";
}