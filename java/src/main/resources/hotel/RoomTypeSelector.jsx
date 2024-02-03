import React, { useEffect, useState } from 'react'
import { getRoomTypes } from './aptions'

const  RoomTypeSelector = ({handleRoomUnputChange,newRoom}) => {
    const[RoomTypes, SetRoomTypes]=useState([""])
    const[showNewRoomTypeInput,SetshowRoomTypeInput]=useState(false)
    const[NewRoomType,SetNewRoomType]=useState("")
    useEffect(()=>{
      getRoomTypes.then((data)=>{
        SetRoomTypes(data)
      })
    },[])

    const handleNewRoominputChange=(e)=>{
        SetNewRoomType(e.target.value);
    }
    const handleAddNewRoomType = () =>{
        if(NewRoomType !==""){
            SetRoomTypes([...RoomTypes,NewRoomType])
            SetNewRoomType("")
            SetshowRoomTypeInput(false)
        }
    }
    return (
        <>
        {RoomTypes.length>0 && (
            <div>
                <select>
                    id:'roomType'
                    name:'roomType'
                    value={newRoom.RoomTypes}
                    onChange={(e)=>{
                        if(e.target.value=="Add new"){
                            SetshowRoomTypeInput(true)
                        }else{
                            handleNewRoominputChange(e)
                        }
                    }}
                    <option value={""}>select a roomType</option>
                    <option value={"Add new"}> Add New</option>
                    {RoomTypes.map((type,index)=>(
                        <option key={index} value={type}>{type}</option>
                    ))}
                </select>
                {showNewRoomTypeInput && (
                    <div className='input-group'>
                        <input 
                        className='form-control'
                        type='text'
                        placeholder='Enter a new roomType'
                        onChange={handleNewRoominputChange}></input>
                        <button className='btn btn-hotrl' type='button' onClick={handleAddNewRoomType}>

                        </button>
                        </div>

                )}
            </div>
        )
            

        }
        </>
    )
}
export default RoomTypeSelector