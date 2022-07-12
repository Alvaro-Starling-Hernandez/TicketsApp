package com.aliens.ticketsapp.di

import android.content.Context
import androidx.room.Room
import com.aliens.ticketsapp.data.TicketsDb
import com.aliens.ticketsapp.data.daos.RespuestaDao
import com.aliens.ticketsapp.data.daos.TecnicoDao
import com.aliens.ticketsapp.data.daos.TiempoDao
import com.aliens.ticketsapp.data.repositories.RespuestaRepository
import com.aliens.ticketsapp.data.repositories.TecnicoRepository
import com.aliens.ticketsapp.data.repositories.TiempoRepository
import com.aliens.ticketsapp.model.Tecnico
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideTicketsDb(@ApplicationContext context: Context): TicketsDb {
        return Room.databaseBuilder(
            context,
            TicketsDb::class.java,
            "TicketsDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvidesTiempoDAO(ticketsDb: TicketsDb): TiempoDao {
        return ticketsDb.tiempoDao
    }

    @Provides
    fun ProvidesTiempoRepository(tiempoDao: TiempoDao): TiempoRepository {
        return TiempoRepository(tiempoDao)
    }

    @Provides
    fun ProvidesRespuestaDAO(ticketsDb: TicketsDb): RespuestaDao {
        return ticketsDb.respuestaDao
    }

    @Provides
    fun ProvidesRespuestaRepository(respuestaDao: RespuestaDao): RespuestaRepository {
        return RespuestaRepository(respuestaDao)
    }


}