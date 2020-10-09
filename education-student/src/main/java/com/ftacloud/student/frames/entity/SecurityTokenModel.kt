package com.ftacloud.student.frames.entity

/**
 * Created by Wangsw on 2020/6/13 0013 22:25.
 * </br>
 *
 */
class SecurityTokenModel {

    var RegionId: String = ""
    var AccessBucketName: String = ""
    var AccessBucketNamePublic: String = ""

    var AccessKeyId: String = ""
    var AccessKeySecret: String = ""
    var SecurityToken: String = ""

    /**
     * 过期时间
     */
    var ExpireTimeSeconds: String = ""
    var StoreDir: String = ""

}

/*
{
    "msg": "ok",
    "code": "200",
    "data": {
        "AccessBucketName": "readingkids-private",
        "SecurityToken": "CAISjwJ1q6Ft5B2yfSjIr5f4GNfMqqkV862GU07cgkVhf/1/3KDZ1Dz2IHtPfnZtCO0ds/83lWBS7PcclrldV5ZOQUvZYY5Z5Z9Q7AW9OwocBn4Nq+5qsoasPETOISyZtZagXoeUZdfZfejXHDKgvyRvwLz8WCy/Vli+S/OggoJmadJlMWvVaiFdVpUsZWkEksIBMmbLPvuAKwPjhnGqbHBloQ1hk2hym8Pdp8SX8UjZl0aoiL1X9cbTWsH8MpE8YMcvDonkg7ApKvb7vXQOu0QQxsBfl7dZ/DrLhNaZDmRK7g+OW+iuqYY0dlAkN/VhRvUc/aimz6wmoJTdi438zxFQMaROTz+aXoml0DtnpA0cqVBQGoABev+BsujE79oEnQDW+FdU360GWaqgAi2WmkPqZKYFGwV/biu1Q+7RdX5qrWCe8y9xDa3bAysobShkhJv1hkAhvVFhq32SL6YP0A8EXUsgp1jimF/63PPYPG/7dwEvOat3WdC1GPvqtMAqJvT33lpWEOjuaQbu42uKnLNUvLAAPa0=",
        "Endpoint": "https://oss-cn-beijing.aliyuncs.com",
        "AccessKeyId": "STS.NTMSmxGv4DomQhmfE4srS3er6",
        "AccessKeySecret": "DeQYtXegoREHwuWA9Lhwxdp7K2H6kxXQ4aLsLpekrVWH",
        "AccessBucketNamePublic": "readingkids-public",
        "RegionId": "oss-cn-beijing",
        "ExpireTimeSeconds": "900",
        "_ExpireTimeSeconds": "900",
        "StoreDir": "ftacloud-example"
    },
    "sysTime": "1602212044204"
}

*/
