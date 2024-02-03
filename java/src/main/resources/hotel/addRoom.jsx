import React, { useState } from "react"
import { addRoom } from "../hotel/aptions"
const addRoom = () => {
    const[newRoom, setNewRoom]=useState({
        photo:null,
        roomType :"",
        roomPrice : ""
    })
    const[imagePreview, setImagePreview] = useState(" ")
    const[successMessage, setSuccessMessage] = useState(" ")
    const[errorMessage,setErrorMesssage]=useState(" ")

    const handleRoomInputchange=(e)=>{
        const name=e.target.name
        let value=e.target.value
        if(name="roomPrice"){
            if(!isNaN(value)){
                value.paseInt(value)
            }else{
                value=""
            }
        }
        setNewRoom({...newRoom,[name]:value})
    }
    const handleImageChange=()=>{
        const selectedImage=e.target.files[0]
        setNewRoom({...newRoom,photo:selectedImage})
        setImagePreview(URL.createObjectURL(selectedImage))
    }
    const handleSubmit=async (e) => {
        e.preventDefault()
        try{
            const success=await addRoom(newRoom.photo,newRoom.roomType,newRoom.roomPrice)
            if(success !==undefined){
                setErrorMesssage("A new room was added to the database!")
                setNewRoom({photo:null,roomType: "", roomPrice: ""})
                setImagePreview("")
                setErrorMesssage("")
            }else{
                setErrorMesssage("Error adding")
            }
        }catch(error){
        setErrorMesssage(error.message)
        }
    }
return(
    <selection className="container , mt-5 mb-5" >
    <div className="row justify-content-center">
      <div className="col-md-8 col-lg-6">
        <h2 className="mt-5 mb-5"> Add a new Room</h2>
        <form onSubmit={handleSubmit}>
            <div className="mb-3">
                <label htmlFor="roomType" className="form-label">Room Type</label>
            <div>
            </div>
            </div>
            <div className="mb-3">
                <label htmlFor="roomPrice" className="form-label">Room Price</label>
        <input
        className="form-control"
        required
        id="roomPrice"
        type="number"
        name="roomPrice"
        value={newRoom.roomPrice}
        onChange={handleRoomInputchange}
        />
            </div>
            <div className="mb-3">
                <label htmlFor="photo" className="form-label">
                    Room Photo
                    </label>
            <input
            id="photo"
            name="photo"
            type="file"
            className="form-control"
            onChange={handleImageChange}
            />
           {imagePreview  &&  (
            <img src={imagePreview}
            alt="Preview Room Photo"
            style={{maxWidth: "400px" ,maxHeight: "400px"}}
            className="mb-3"/>
           )}
            </div>

            <div className="d-grid d-flex mt-2">
                <button className="btn btn-outline-primary ml-5">
                    Save Room
                </button>
            </div>
        </form>
      </div>
    </div>
    </selection>
)
}
export default addRoom