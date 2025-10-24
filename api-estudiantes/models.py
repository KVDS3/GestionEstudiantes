from pydantic import BaseModel, Field
from typing import Optional
from datetime import datetime

class EstudianteBase(BaseModel):
    nombre: str = Field(..., min_length=1, max_length=100)
    edad: int = Field(..., ge=15, le=100)
    carrera: str = Field(..., max_length=100)
    promedio: float = Field(..., ge=0, le=100)

class EstudianteCreate(EstudianteBase):
    pass

class EstudianteUpdate(BaseModel):
    nombre: Optional[str] = Field(None, min_length=1, max_length=100)
    edad: Optional[int] = Field(None, ge=15, le=100)
    carrera: Optional[str] = Field(None, max_length=100)
    promedio: Optional[float] = Field(None, ge=0, le=100)

class EstudianteResponse(EstudianteBase):
    id: int
    fecha_registro: datetime

    class Config:
        from_attributes = True