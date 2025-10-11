const baseurl=" http://localhost:8080/emp";

let resultdiv = document.querySelector("#result");


function displaydata(data){

    // the backend data will be the array of the values so we are using the Array.isArray(data from the backend)
   // the backend data will be the array of the values so we are using the Array.isArray(data from the backend)
    
   if(Array.isArray(data)){
        resultdiv.innerHTML="";
          //creating the new element
          data.forEach((emp)=>{
          const newelement = document.createElement("p");
          newelement.className="box";
          newelement.innerHTML=`
          <p><b>Id: </b> ${emp.id}</p>
          <p><b>Name: </b> ${emp.name}</p>
          <p><b>Salary: </b> ${emp.salary}</p>
          `;
          resultdiv.appendChild(newelement);
          })
    }

    // if backend sends the only single object
    else if(typeof data == "object"){
        resultdiv.className="box";
        resultdiv.innerHTML=`
        <p><b>Id: </b> ${data.id}</p>
        <p><b>Name: </b> ${data.name}</p>
        <p><b>Salary: </b> ${data.salary}</p>`;
    }
    // if the backend sends the message it will go to this condition
    else{
        resultdiv.textContent=data;
    }
}

// Getting all the Emp Details With the GetAllBtn.
  let getAllEmp = document.querySelector("#getallemp");
  getAllEmp.addEventListener("click",  async ()=>{
  document.querySelector("#inp").innerHTML="";
  document.querySelector("#label").innerHTML="";

    try{
        let response = await fetch(baseurl);
        let datavalue = await response.json();
        displaydata(datavalue);
    }
    catch(err){
        resultdiv.innerHTML=`<p style="color:red">Error Message</p>`
    }
})

///getting the each emp data using the id..

let empbyid = document.querySelector("#GetEmpByIdBtn");
 empbyid.addEventListener("click",async ()=>{
 resultdiv.innerHTML="";
 document.querySelector("#inp").style.display="inline-block";
 document.querySelector("#label").style.display="inline-block";

 const getid=document.querySelector("#inp").value;
 if(!getid){
    return alert("Enter the Empid");
 }

 try{
       let response=await fetch(`${baseurl}/${getid}`);
       let data12= await response.json();
       displaydata(data12);
 }
 catch(err){
    resultdiv.innerHTML=`<p style="color:red">Error Message ${err}</p>`
 }
})

// to delete an employee
let deletedval = document.querySelector("#delbtn");

deletedval.addEventListener("click",async()=>{
       resultdiv.innerHTML="";
      let editcontainer = document.querySelector(".editcontainer");
    //   editcontainer.innerHTML=""
    document.querySelector("#inp").style.display="inline-block";
    document.querySelector("#label").style.display="inline-block";
    // to make the  space clear we are using this

    let deletedid=document.querySelector("#inp").value;
    if(!deletedid){
        return alert("Enter the id want to delete ");
    }
    if(!confirm(deletedid)){
        return alert("Are u sure want to delete");
    }
    try{
        let response=await fetch(`${baseurl}/${deletedid}`,{method:"DELETE"});
        if(response.ok){
            resultdiv.innerHTML=`<p style="color:red">Employee Deleted Successfully ${deletedid}</p>`
        }
        else{
            resultdiv.innerHTML=`<p style="color:green"> Employee Not Deleted Successfully ${deletedid}<p>`
        }
    }
    catch(err){
         resultdiv.innerHTML=`<p style="color:red">Getting some Error ${JSON.stringify(err)} </p>`
    }
})


// To Update an Employee


let updateBtn = document.querySelector(".updatebtn");
let editcontainer = document.querySelector(".editcontainer");
let submitbtn = document.querySelector("#subtn");

updateBtn.addEventListener("click",()=>{
       resultdiv.innerHTML="";
       editcontainer.style.display="inline-block";
})


submitbtn.addEventListener("click",async ()=>{
let id=document.querySelector("#inpid").value;
let name=document.querySelector("#nameInp").value;
let salary=document.querySelector("#numInp").value;


if(!id || !name || !salary){
   alert("Please Fill all the Fields");
   return;
}

try{
    const response=await fetch(`${baseurl}/${id}`,{method:"PUT",headers:{"Content-type":"application/json"}, body: JSON.stringify({ name, salary })})
if(response.ok){
          const data=await response.json();   
        //   document.getElementById("resdata").innerText = `Employee updated: = ${JSON.stringify(data)}`;
        // it will print the data in the form of the array of the object to the json format.. 
        displaydata(data);             
        //   document.getElementById("resdata").innerText = `Employee updated: = ${data}`;
        }
else {
      document.getElementById("resdata").innerText = " Failed to update employee";
    }
}
catch(err){

    document.getElementById("resdata").innerText = " Error updating employee";
}
})






