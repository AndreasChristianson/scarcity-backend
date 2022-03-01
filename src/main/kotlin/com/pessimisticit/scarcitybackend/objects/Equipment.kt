package com.pessimisticit.scarcitybackend.objects

import com.pessimisticit.scarcitybackend.constants.BindType

open class Equipment : GameObject() {
    var bindType: BindType = BindType.NEVER_BINDS
    var weight:Double = 0.0
}
