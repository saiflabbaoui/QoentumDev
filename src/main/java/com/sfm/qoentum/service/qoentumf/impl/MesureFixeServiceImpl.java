package com.sfm.qoentum.service.qoentumf.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sfm.qoentum.dao.qoentumf.MesureFixeDao;
import com.sfm.qoentum.dto.AvgMinMaxPerDate;
import com.sfm.qoentum.dto.DateStartEnd;
import com.sfm.qoentum.dto.EntityPage;
import com.sfm.qoentum.dto.LabelRecordsPercent;
import com.sfm.qoentum.dto.PageUtil;
import com.sfm.qoentum.dto.StatParSeuil;
import com.sfm.qoentum.enumer.EnumGranularite;
import com.sfm.qoentum.model.qoentumf.MesureFixe;
import com.sfm.qoentum.model.qoentumf.SeuilFixe;
import com.sfm.qoentum.service.qoentumf.MesureFixeService;

@Service(value = "mesureFixeService")
public class MesureFixeServiceImpl implements MesureFixeService {

	@Autowired
	private MesureFixeDao mesureFixeDao;

	@Override
	public MesureFixe save(MesureFixe mesureFixe) {
		return mesureFixeDao.save(mesureFixe);
	}

	@Override
	public List<MesureFixe> findAll() {
		List<MesureFixe> list = new ArrayList<>();
		mesureFixeDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		mesureFixeDao.deleteById(id);

	}

	@Override
	public MesureFixe findById(Long id) {
		return mesureFixeDao.findById(id).get();
	}

	@Override
	public long count() {
		return mesureFixeDao.count();
	}

	@Override
	public EntityPage<MesureFixe> listMesureFixe(long sonde_id, long fournisseur_acces_id, long indicateur_fixe_id,
			long emplacement_id, Date start, Date end, Pageable pageable) {

		Page<MesureFixe> mesureFixesPage = mesureFixeDao
				.findBySondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetween(sonde_id,
						fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end, pageable);
		
		System.out.println("----------------> from database " + mesureFixesPage.getContent().size());

		EntityPage<MesureFixe> mesureFixes = new EntityPage<MesureFixe>();

		mesureFixes.setList(mesureFixesPage.getContent());

		PageUtil pageUtil = new PageUtil();
		pageUtil.setNombreElementParPage(mesureFixesPage.getNumberOfElements());
		pageUtil.setNombrePage(mesureFixesPage.getTotalPages());
		pageUtil.setNumeroPage(mesureFixesPage.getNumber() + 1);
		pageUtil.setNombreTotalElement(mesureFixesPage.getTotalElements());

		mesureFixes.setPageUtil(pageUtil);

		return mesureFixes;

	}

	@Override
	public StatParSeuil statPercent(List<SeuilFixe> plages, long sonde_id, long fournisseur_acces_id,
			long indicateur_fixe_id, long emplacement_id, Date start, Date end) {

		List<LabelRecordsPercent> labelRecordsPercents = new ArrayList<>();

		int totalRecords = mesureFixeDao
				.countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetween(
						sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end);

		System.out.println("----------------> totalRecords" + totalRecords);

		if (totalRecords == 0) {
			return new StatParSeuil(totalRecords, labelRecordsPercents);
		}

		for (SeuilFixe plage : plages) {
			System.out.println("----------------> Seuil : " + plage.getLibelle());

			int records = 0;

			if (plage.getBorneSup() == null) {
				// On a uniquement la borne inférieure
				Double borneInfTest;
				if (plage.getIncluseInf() == true) {
					// La borne est Incluse
					borneInfTest = plage.getBorneInf();
				} else {
					// La borne n'est pas incluse
					borneInfTest = plage.getBorneInf() + 1;
				}

				records = mesureFixeDao
						.countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureGreaterThanEqual(
								sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end,
								borneInfTest);
			} else if (plage.getBorneInf() == null) {
				// On a uniquement la borne supérieure
				Double borneSupTest;
				if (plage.getIncluseSup() == true) {
					// La borne est Incluse
					borneSupTest = plage.getBorneSup();
				} else {
					// La borne n'est pas incluse
					borneSupTest = plage.getBorneSup() - 1;
				}

				records = mesureFixeDao
						.countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureLessThanEqual(
								sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end,
								borneSupTest);
			} else {
				// On a les deux bornes
				Double borneInfTest;
				if (plage.getIncluseInf() == true) {
					// La borne est Incluse
					borneInfTest = plage.getBorneInf();
				} else {
					// La borne n'est pas incluse
					borneInfTest = plage.getBorneInf() + 1;
				}

				Double borneSupTest;
				if (plage.getIncluseSup() == true) {
					// La borne est Incluse
					borneSupTest = plage.getBorneSup();
				} else {
					// La borne n'est pas incluse
					borneSupTest = plage.getBorneSup() - 1;
				}

				records = mesureFixeDao
						.countByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenAndMesureBetween(
								sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id, start, end,
								borneInfTest, borneSupTest);

			}

			System.out.println("----------------> records" + records);

			double percent = ((double) records / totalRecords) * 100;

			labelRecordsPercents.add(new LabelRecordsPercent(plage.getLibelle(), percent, records, plage.getColor()));

		}

		return new StatParSeuil(totalRecords, labelRecordsPercents);

	}

	@Override
	public List<AvgMinMaxPerDate> findAvgMinMaxPerDate(long sonde_id, long fournisseur_acces_id,
			long indicateur_fixe_id, long emplacement_id, Date start, Date end, EnumGranularite granularite) {

		List<AvgMinMaxPerDate> resultList = new ArrayList<>();

		// Récupérer la liste des dates en fonction de la granularité entre les deux
		// dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		start = cal.getTime();

		System.out.println("start date --------->" + start);

		cal.setTime(end);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		end = cal.getTime();

		System.out.println("end date --------->" + end);

		List<Date> datesBetween = getDatesBetween(start, end);

		System.out.println("dates between --------->" + datesBetween);

		// Générer la liste des dates debut et fin en fonction de la granularité
		List<DateStartEnd> dateStartEnds = generatesDateStartDebut(datesBetween, granularite);

		// Récupérer depuis le DAO tous les résultats un à un par intervalle de la
		// granularité selon l'indicateur mobile

		for (DateStartEnd dateStartEnd : dateStartEnds) {

			AvgMinMaxPerDate avgMinMaxPerDate = mesureFixeDao.findMesureAvgMinMaxPerDate(sonde_id, fournisseur_acces_id,
					indicateur_fixe_id, emplacement_id, dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());
			avgMinMaxPerDate.setDate(dateStartEnd.getDateStart().getTime());
			
			List<MesureFixe> ms = mesureFixeDao
					.findAllByMesureIsNotNullAndSondeIdAndFournisseurAccesIdAndIndicateurFixeIdAndEmplacementIdAndDateBetweenOrderByMesureAsc(
							sonde_id, fournisseur_acces_id, indicateur_fixe_id, emplacement_id,
							dateStartEnd.getDateStart(), dateStartEnd.getDateEnd());

			if (ms.isEmpty())
				avgMinMaxPerDate.setMedian(0);

			else {
				if (ms.size() % 2 == 0)
					avgMinMaxPerDate.setMedian(
							(ms.get(ms.size() / 2).getMesure() + ms.get((ms.size() / 2) - 1).getMesure()) / 2);
				else
					avgMinMaxPerDate.setMedian(ms.get(ms.size() / 2).getMesure());
			}

			resultList.add(avgMinMaxPerDate);
		}

		return resultList;
	}

	public static List<Date> getDatesBetween(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}

	public static List<DateStartEnd> generatesDateStartDebut(List<Date> dates, EnumGranularite granularite) {
		List<DateStartEnd> dateStartEnds = new ArrayList<>();

		if (granularite.equals(EnumGranularite.Heure)) {
			for (Date date : dates) {
				for (int i = 0; i < 24; i++) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);

					cal.set(Calendar.HOUR_OF_DAY, i);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					Date start = cal.getTime();

					cal.set(Calendar.HOUR_OF_DAY, i);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.SECOND, 59);
					Date end = cal.getTime();

					dateStartEnds.add(new DateStartEnd(start, end));

				}
			}
		} else {

			for (Date date : dates) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date start = cal.getTime();

				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 59);
				Date end = cal.getTime();

				dateStartEnds.add(new DateStartEnd(start, end));
			}

		}

		System.out.println("--------> les dates : " + dateStartEnds);

		return dateStartEnds;
	}

}
