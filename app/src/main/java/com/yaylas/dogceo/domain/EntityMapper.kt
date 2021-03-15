package com.yaylas.dogceo.domain

interface EntityMapper <Entity, DomainModel>{

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>
}