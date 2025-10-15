
    // Important: Image data is byte[], so you cannot directly put it in HTML. 
    // You need to convert it to Base64 (browser understandable format).
    const baseurl = "http://localhost:8080/img";
    let imageContainer = document.querySelector(".imageContainer");  // where the images can be display
    let getAllImg = document.querySelector("#getAllbtn");


    async function FetchAllImages() {
      try {

        // this async always returns the promise..
        // to get the data from the backend, we are using the fetch method
        // the async function return the promise we need to wait untill the 
        // promise is resolved so we are using the await keyword
        // to wait the process, we will get  the response from the backend,
     

        let response = await fetch(baseurl);

        let EachImg = await response.json();
        //this is the each image array  like the object  
        // after getting the response from the backend, 
        // we need to get data json format that is javascript object notation
        // so it need untill the above the reponse is resolved


        imageContainer.innerHTML = "";

        // we are using the For Each To Iterate The  Each Image in the Backend

          EachImg.forEach((img) => {
          let eachImageContainer = document.createElement("div");
          eachImageContainer.className = "image-container";
        //   eachImageContainer.style.display = "flex";
        //   eachImageContainer.style.flexDirection = "column";
          eachImageContainer.style.padding = "10px";
          eachImageContainer.style.position="relative"





          // creating the each image element for the each there should be the sepparte
          let eachImgTag = document.createElement("img");
          eachImgTag.className = "img";
          eachImgTag.src = `data:${img.imagetype};base64,${img.imagedata}`;
          // conveting the byte array of the img data into the base 64 format 
          // only the imagetype and the image data , here converting the image 
         //  data to the base 64 format.. 
          eachImgTag.style.width = "300px";
          eachImgTag.style.margin = "10px";




          //btn container for the two buttons that is the edit and the delete.
          let btnContainer = document.createElement("div");
          btnContainer.className="Twobtns";
          btnContainer.style.display = "flex";
          btnContainer.style.justifyContent = "flex-start"; // align buttons to right
          btnContainer.style.marginTop = "1px";
          btnContainer.style.marginLeft = "20px";
          btnContainer.style.gap = "8px";


         //in the each editbtn  we are getting the data-index 
        //  which image belong to which id
        // we are setting the setAttribute(name,value)
        // name means the data index, value means the img.id  
          let editbutton = document.createElement("button");
          editbutton.className = "btn"
          editbutton.setAttribute("data-index", img.id);
          editbutton.textContent = "Edit";
          editbutton.style.borderRadius = "2px";
          editbutton.style.padding = "5px 10px";
          editbutton.style.cursor = "pointer";
          editbutton.style.border = "none";


        //   this is th delete btn in the each image
          let deletebtn = document.createElement("button");
          deletebtn.className = "btn";
          deletebtn.setAttribute("data-index", img.id);
          deletebtn.textContent = "Delete";
          deletebtn.style.borderRadius = "2px";
          deletebtn.style.border = "none";
          deletebtn.style.padding = "5px 10px";
          deletebtn.style.cursor = "pointer";





          // from here onwards we are adding the addEventListener 
          // for the edit button and delete button...


          editbutton.addEventListener("click", async () => {
              // let imageContainer = document.querySelector(".imageContainer"); 
              // imageContainer.style.display="none";

            //   when the edit btn is clicked the main container should be the disapper

                let main = document.querySelector(".main-container");
                main.style.display="none";

            // Modal Creation  i,e is the entire one div tag
             // in the short entire box for the updation..
            let modal = document.createElement("div");
            // modal.id = `edit-modal-${img.id}`;
            modal.style.left = "0";
            modal.style.width = "100%";
            modal.style.height = "100%";
            modal.style.display = "flex";
            modal.style.alignItems = "center"
            modal.style.justifyContent = "center";
            // modal.style.position="fixed";


            // Form Container 
            const card = document.createElement("div");
            card.style.padding = "20px";
            card.style.width = "400px";
            card.style.position="absolute";
            card.style.backgroundColor="#8B7E74"
            card.style.borderRadius="15px";
            card.style.borderColor="black";
            card.style.top="150px";
            


            // title

            let title = document.createElement("h3");
            title.textContent = "Edit Image";
            title.style.color=""


            // Current Image Preview
            let preview = document.createElement("img");
            // converting byte array too the browser readable format
            preview.src = `data:${img.imagetype};base64,${img.imagedata}`;
            preview.style.width = "100%";
            preview.style.height = "200px";
            preview.style.objectFit = "cover";
            preview.style.borderRadius = "5px";


            // image name

            let nameLabel = document.createElement("label");
            nameLabel.textContent = "Image Name";
            nameLabel.style.display = "block";



            // name input Tag..
            const nameInput = document.createElement("input");
            nameInput.type = "text";
            nameInput.value = img.imagename || "";   // Prefill with current name 
                                                     // are also the we can add the new value
            nameInput.style.width = "100%";
            nameInput.style.padding = "8px";
            nameInput.style.marginTop = "5px";
            nameInput.style.boxSizing = "border-box";




            // File Input actula Uplading the image

            const fileinput = document.createElement("input");
            fileinput.type = "file";
            fileinput.accept = "image/*";       //only images are allowed
            fileinput.style.marginTop = "5px";

            let buttonContainer = document.createElement("div");
            buttonContainer.style.display = "flex";
            buttonContainer.style.marginTop = "5px";
            buttonContainer.style.display = "flex-end";
            buttonContainer.style.gap = "10px";

            //  Cancel button
            const cancelBtn = document.createElement("button");
            cancelBtn.textContent = "Cancel";
            cancelBtn.style.padding = "6px 20px"; //top and the bottom  is the 6px, 
                                                 // left right to be the 20px  
            cancelBtn.style.cursor = "pointer";



            cancelBtn.addEventListener("click", () => {
              modal.remove(); //close the modal
             // FetchAllImages();
                //   let main = document.querySelector(".main-container");
                // main.style.display="inline-block";
            })


            // save Button

        //   here the crucial part like the updating the 
        // image and the fetching the data from the backend  
            const saveBtn = document.createElement("button");
            saveBtn.textContent = "Save";
            saveBtn.style.padding = "6px 12px";
            saveBtn.style.cursor = "pointer";
            saveBtn.style.backgroundColor = "#278aecff";
            saveBtn.style.color = "white";
            saveBtn.style.border = "none";
            saveBtn.style.borderRadius = "4px";




            // here when the user clicks the savebtn  
            saveBtn.addEventListener("click", async () => {
              let newname = nameInput.value;  
              const file = fileinput.files[0] //get new file if choosen

              const formData = new FormData();

              formData.append("imagename", newname || img.imagename); // always send name
            //   here we are using the or gate means either the new name or old name can be considered..
              if (file) formData.append("file", file);        //if the user clicks the file append to the
              //  file name            
              // // optional
              //appends the new name;


              try {
                const response = await fetch(`${baseurl}/${img.id}`, {
                  method: "PUT",
                  body: formData        //here sending the appended data to the 
                });

                if (response.ok) {       //consider the backkend the 
                                        // image was there it will give the result true..
                                        // Update image preview in the UI
                  if (file) {                  //if the new file was uploaded
                    const reader = new FileReader();       // this filreader  are used to read the file , and img file to the base 64string 
                    reader.onload = () => {
                      eachImgTag.src = reader.result;   // Replace updated image to the again to the eachimgta.through the src
                      img.imagename = newname;           // Update local data updating the image name
                    };
                    reader.readAsDataURL(file);   //reads the binary file to the encoded base64 string
                  } else {
                    img.imagename = newname;           // Only name changed  //consider user updates only the name
                  }

                  modal.remove(); // Close modal
                  alert("Image updated successfully!");
                } else {
                  alert("Failed to update image");
                }
              } catch (err) {
                console.error(err);
                alert("Error updating image");
              }

            })
  

            // all the data fetched from the backend 

            // now we need to append all the items to the container

            // adding all the data to the modal

        //    in the btn container we are adding the two btn's
            buttonContainer.appendChild(saveBtn);
            buttonContainer.appendChild(cancelBtn);


            // in the form container we adding the 
            // title,preview , namelabel,nameI/p ,file Input and btn Container..
            card.appendChild(title);
            card.appendChild(preview);
            card.appendChild(nameLabel);
            card.appendChild(nameInput);
            card.appendChild(fileinput);
            card.appendChild(buttonContainer);



            // adding all the card items to the modal (Main div for the Updation )
            modal.appendChild(card);


            document.body.appendChild(modal);


          })

          deletebtn.addEventListener("click", async () => {
            // alert(`  ${img.id} `);
            if (!confirm(`Are you Sure want delete the Image ${img.id} `)) {
              // FetchAllImages();
              return;
            }

            try {
              let resp = await fetch(`${baseurl}/${img.id}`, {
                method: 'DELETE'
              })
              let deletedata = await resp.text();

              if (resp.ok) {
                alert(`${deletedata}`);
                FetchAllImages();
              }
              else {
                alert(`${deletedata}`);
              }
            }
            catch (err) {
              console.error(err);
            }


          })






          // append the edit & delete btn in the btncontiner
        //    in the each there is the edit and deletebtn
          btnContainer.appendChild(editbutton);
          btnContainer.appendChild(deletebtn);


          // append the buttons the and the images to the main 
        //   in the eachImage we are adding the two things
        // that is the two btn's and the img  
          eachImageContainer.appendChild(eachImgTag);
          eachImageContainer.appendChild(btnContainer);


          // append the entire image to the and buttons to the entire the image to the
        //   Append the Entire the EachImg Container to the -> Image Container 
          imageContainer.appendChild(eachImageContainer);


        })
      }
      catch (err) {
        console.error("Error Fetching The  Images ", err);
      }
    }


    
// this addevent listener work like this when the user clicks the getAllImg btn the all the Images 
// Will be displayed...
    getAllImg.addEventListener("click", FetchAllImages);


    // to Upload An Image Form
    let uploadForm = document.querySelector("#uploadForm");
    let message = document.querySelector("#dispmessage");

    let uploadurl = "http://localhost:8080/img/upload";


    uploadForm.addEventListener("submit", async (e) => {
        // here we are using the preventDefault because that is the 
        // when the user submits the page will automatically reload to avoid we are using this 
      e.preventDefault();
      // message.innerHTML=""

      let fileinput = document.querySelector("#fileinput");
      const file = fileinput.files[0];   //the first  selected file 

      const formdata = new FormData();

      formdata.append("file", file);


      try {
        const response = await fetch(uploadurl, {
          method: 'POST',
          body: formdata
        });


        if (response.ok) {
          message.innerHTML = `Image Uplaoded Successfully`;
        }
        else {
          message.innerHTML = `Please Add The Image`;
        }
      }
      catch (err) {
        console.error(err);
      }
    })



    // to delete an image 

    // let deletebtn = document.querySelector("#delbtn");
    // const deleteurl="http://localhost:8080/img/id"

    // deletebtn.addEventListener("click", async () => {
    //   document.querySelector(".getContainer").style = "inline-block";
    //   let id = document.querySelector("#numInp").value;
    //   if (!id) {
    //     alert("please Enter the Id");
    //     return;
    //   }


    //   if (!confirm(`Are you sure u want to delete!! ${id}`)) {
    //     return;
    //   }

    //   try {

    //     const resp = await fetch(`${baseurl}/${id}`, {
    //       method: 'DELETE'
    //     });

    //     let data = await resp.text();
    //     alert(`${data}`);

    //     if (resp.ok) {
    //       alert(`${data}`);
    //       FetchAllImages();
    //     }
    //     else {
    //       alert(`${data}`);

    //     }



    //     // clearing the input 
    //     document.querySelector("#numInp").value = "";
    //     document.querySelector("#numInp").style.display = "none";
    //     document.querySelector("#label").style.display = "none";

    //   }
    //   catch (err) {
    //     console.error(err);
    //     alert("Deleteion failed");
    //   }

    // })





