import axios from 'axios';




export const signIn = (userData) => {
    return new Promise(async (resolve, reject) => {
        axios.post('/signIn', userData).then(async (res) => {
            resolve(res)
        })
    })
}


export const signUp = (userData) => {
    return new Promise(async (resolve, reject) => {
        axios.post('/signUp', userData).then(async (res) => {
            resolve(res)
        }).catch((err) => {
            console.error('strapi user register error message : ',err.message)
            reject("이미 가입된 이메일 입니다.")
        })
    })
}

export const getUserByNickname = (nickname) => {
    return new Promise(async (resolve, reject) => {
        try {
        const res = await axios.get(`/users?${nickname}`)
                resolve(res)
        } catch (err) {
            reject("유저(닉네임 조회) 데이터 가져오기 실패")
        }
    })
}