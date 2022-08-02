import React, { useState, useEffect } from 'react';
import HeaderLink from "../../components/HeaderLink/HedaerLink";
import {useAuthState} from "../../atoms";
import './Home.css'

function Home() {
    const [authState, setAuthState] = useAuthState()

    const loginAlert= () => {
        if(!authState.isLoggedIn) {
            alert("로그인을 해주세요")
        }
    }
    return (

        <div className="Home">

            <HeaderLink />
            <input className="postbox" type={"text"}></input>
            <button  disabled={authState.isLoggedIn} type='submit' className={"register"} onClick={loginAlert}>등록</button>

        </div >
    )
}
export default Home