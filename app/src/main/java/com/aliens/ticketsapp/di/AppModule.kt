package com.aliens.ticketsapp.di

import android.content.Context
import androidx.room.Room
import com.aliens.ticketsapp.data.TicketsDb
import com.aliens.ticketsapp.data.daos.*
import com.aliens.ticketsapp.data.repositories.*
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

    @Provides
    fun ProvidesTecnicoRepository(tecnicoDao: TecnicoDao): TecnicoRepository{
        return TecnicoRepository(tecnicoDao)
    }

    @Provides
    fun ProvidesTecnicoDao(ticketsDb: TicketsDb): TecnicoDao{
        return ticketsDb.tecnicoDao
    }

    @Provides
    fun ProvidesClienteRepository(clienteDao: ClienteDao): ClienteRepository{
        return ClienteRepository(clienteDao)
    }

    @Provides
    fun ProvidesClienteDao(ticketsDb: TicketsDb): ClienteDao{
        return ticketsDb.clienteDao
    }

    @Provides
    fun ProvidesTicketRepository(ticketDao: TicketDao): TicketRepository{
        return TicketRepository(ticketDao)
    }

    @Provides
    fun ProvidesTicketDao(ticketsDb: TicketsDb): TicketDao{
        return ticketsDb.ticketDao
    }

}