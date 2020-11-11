package com.fatcloud.account.frames.entity.question

data class Question(
    var iconImg: String = "",
    var introduce: String = "",
    var items: ArrayList<QuestionChild> = ArrayList(),
    var name: String = "",
    var stage: String = "",
    var type: String = ""
)

/*

"data": {
    "name": "测试题5题目",
    "introduce": "测试题5题目（带读音题）",
    "iconImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrVkpD3ck8XROWfYAGNaCNEAicra8Fx7ErwNclsul2JjKEPIw88ia9d1zBVAiaxn2THhGKUqQmdyw4A/132",
    "stage": "S0-无基础",
    "type": "GRADING-定级考试",
    "items": [
    {
        "itemType": "SELECT-选择题",
        "itemImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrVkpD3ck8XROWfYAGNaCNEAicra8Fx7ErwNclsul2JjKEPIw88ia9d1zBVAiaxn2THhGKUqQmdyw4A/132"
    },
    {
        "itemType": "FILL_IN_THE_BLANKS-填空题",
        "itemImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIrVkpD3ck8XROWfYAGNaCNEAicra8Fx7ErwNclsul2JjKEPIw88ia9d1zBVAiaxn2THhGKUqQmdyw4A/132"
    },
    {
        "itemType": "SOUND_RECORDING-录音题",
        "itemImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLiaLNnO8UKw5l3Jno0QpUHj8Ipzwx3wvm4KiboJnGWIJbhW10XZL73REVz7iaNFzwygqDHic2yMp2Vkg/132"
    }
    ]
}*/
